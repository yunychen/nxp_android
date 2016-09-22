/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.cts.deviceowner;

import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import static com.android.cts.deviceowner.FakeKeys.FAKE_DSA_1;
import static com.android.cts.deviceowner.FakeKeys.FAKE_RSA_1;

public class CaCertManagementTest extends BaseDeviceOwnerTest {
    /**
     * Test: device admins should be able to list all installed certs.
     *
     * <p>The list of certificates must never be {@code null}.
     */
    public void testCanRetrieveListOfInstalledCaCerts() {
        List<byte[]> caCerts = mDevicePolicyManager.getInstalledCaCerts(getWho());
        assertNotNull(caCerts);
    }

    /**
     * Test: a valid cert should be installable and also removable.
     */
    public void testCanInstallAndUninstallACaCert()
            throws CertificateException, GeneralSecurityException {
        assertUninstalled(FAKE_RSA_1.caCertificate);
        assertUninstalled(FAKE_DSA_1.caCertificate);

        assertTrue(mDevicePolicyManager.installCaCert(getWho(), FAKE_RSA_1.caCertificate));
        assertInstalled(FAKE_RSA_1.caCertificate);
        assertUninstalled(FAKE_DSA_1.caCertificate);

        mDevicePolicyManager.uninstallCaCert(getWho(), FAKE_RSA_1.caCertificate);
        assertUninstalled(FAKE_RSA_1.caCertificate);
        assertUninstalled(FAKE_DSA_1.caCertificate);
    }

    /**
     * Test: removing one certificate must not remove any others.
     */
    public void testUninstallationIsSelective()
            throws CertificateException, GeneralSecurityException {
        assertTrue(mDevicePolicyManager.installCaCert(getWho(), FAKE_RSA_1.caCertificate));
        assertTrue(mDevicePolicyManager.installCaCert(getWho(), FAKE_DSA_1.caCertificate));

        mDevicePolicyManager.uninstallCaCert(getWho(), FAKE_DSA_1.caCertificate);
        assertInstalled(FAKE_RSA_1.caCertificate);
        assertUninstalled(FAKE_DSA_1.caCertificate);

        mDevicePolicyManager.uninstallCaCert(getWho(), FAKE_RSA_1.caCertificate);
    }

    /**
     * Test: uninstallAllUserCaCerts should be equivalent to calling uninstallCaCert on every
     * supplementary installed certificate.
     */
    public void testCanUninstallAllUserCaCerts()
            throws CertificateException, GeneralSecurityException {
        assertTrue(mDevicePolicyManager.installCaCert(getWho(), FAKE_RSA_1.caCertificate));
        assertTrue(mDevicePolicyManager.installCaCert(getWho(), FAKE_DSA_1.caCertificate));

        mDevicePolicyManager.uninstallAllUserCaCerts(getWho());
        assertUninstalled(FAKE_RSA_1.caCertificate);
        assertUninstalled(FAKE_DSA_1.caCertificate);
    }

    private void assertInstalled(byte[] caBytes)
            throws CertificateException, GeneralSecurityException {
        Certificate caCert = readCertificate(caBytes);
        assertTrue(isCaCertInstalledAndTrusted(caCert));
    }

    private void assertUninstalled(byte[] caBytes)
            throws CertificateException, GeneralSecurityException {
        Certificate caCert = readCertificate(caBytes);
        assertFalse(isCaCertInstalledAndTrusted(caCert));
    }

    /**
     * Whether a given cert, or one a lot like it, has been installed system-wide and is available
     * to all apps.
     *
     * <p>A CA certificate is "installed" if it matches all of the following conditions:
     * <ul>
     *   <li>{@link DevicePolicyManager#hasCaCertInstalled} returns {@code true}.</li>
     *   <li>{@link DevicePolicyManager#getInstalledCaCerts} lists a matching certificate (not
     *       necessarily exactly the same) in its response.</li>
     *   <li>Any new instances of {@link TrustManager} should report the certificate among their
     *       accepted issuer list -- older instances may keep the set of issuers they were created
     *       with until explicitly refreshed.</li>
     *
     * @return {@code true} if installed by all metrics, {@code false} if not installed by any
     *         metric. In any other case an {@link AssertionError} will be thrown.
     */
    private boolean isCaCertInstalledAndTrusted(Certificate caCert)
            throws GeneralSecurityException, CertificateException {
        boolean installed = mDevicePolicyManager.hasCaCertInstalled(getWho(), caCert.getEncoded());

        boolean listed = false;
        for (byte[] certBuffer : mDevicePolicyManager.getInstalledCaCerts(getWho())) {
            if (caCert.equals(readCertificate(certBuffer))) {
                listed = true;
            }
        }

        boolean trusted = false;
        final TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);
        for (TrustManager trustManager : tmf.getTrustManagers()) {
             if (trustManager instanceof X509TrustManager) {
                final X509TrustManager tm = (X509TrustManager) trustManager;
                if (Arrays.asList(tm.getAcceptedIssuers()).contains(caCert)) {
                    trusted = true;
                }
            }
        }

        // All three responses should match - if an installed certificate isn't trusted or (worse)
        // a trusted certificate isn't even installed we should 
        assertEquals(installed, listed);
        assertEquals(installed, trusted);
        return installed;
    }

    /**
     * Convert an encoded certificate back into a {@link Certificate}.
     *
     * Instantiates a fresh CertificateFactory every time for repeatability.
     */
    private static Certificate readCertificate(byte[] certBuffer) throws CertificateException {
        final CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        return certFactory.generateCertificate(new ByteArrayInputStream(certBuffer));
    }
}
