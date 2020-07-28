/*
 * Copyright (C)2016 - SMBJ Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hierynomus.smbj.session;

import com.hierynomus.mssmb2.messages.SMB2SessionSetup;
import com.hierynomus.smbj.connection.SMBSessionBuilder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Set;

public class SessionContext {
    private Set<SMB2SessionSetup.SMB2SessionFlags> sessionFlags;

    private boolean signingRequired;
    private boolean encryptData;

    private SecretKey sessionKey;

    private SecretKey signingKey;
    // SMB 3.x
    private SecretKey decryptionKey;

    // SMB 3.1.1
    byte[] preauthIntegrityHashValue;

    public void established(SMB2SessionSetup response) {
        this.sessionFlags = response.getSessionFlags();

    }


    public boolean isSigningRequired() {
        return signingRequired;
    }

    public void setSigningRequired(boolean signingRequired) {
        this.signingRequired = signingRequired;
    }

    public boolean isEncryptData() {
        return encryptData;
    }

    public void setEncryptData(boolean encryptData) {
        this.encryptData = encryptData;
    }

    public boolean isAnonymous() {
        return sessionFlags.contains(SMB2SessionSetup.SMB2SessionFlags.SMB2_SESSION_FLAG_IS_NULL);
    }

    public boolean isGuest() {
        return sessionFlags.contains(SMB2SessionSetup.SMB2SessionFlags.SMB2_SESSION_FLAG_IS_GUEST);
    }

    public void setSessionKey(byte[] sessionKey) {
        this.sessionKey = new SecretKeySpec(sessionKey, "");
    }

    public void setPreauthIntegrityHashValue(byte[] preauthIntegrityHashValue) {
        this.preauthIntegrityHashValue = Arrays.copyOf(preauthIntegrityHashValue, preauthIntegrityHashValue.length);
    }

    public byte[] getPreauthIntegrityHashValue() {
        return preauthIntegrityHashValue;
    }

    public SecretKey getSessionKey() {
        return sessionKey;
    }

    public SecretKey getSigningKey() {
        return signingKey;
    }

    public SecretKey getDecryptionKey() {
        return decryptionKey;
    }
}
