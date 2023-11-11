package io.github.awidesky.jCipherUtil.util.cipherEngine;

import java.util.function.Function;

import javax.crypto.Cipher;

import io.github.awidesky.jCipherUtil.util.CipherMode;

/**
 * A {@code CipherEncryptEngine} is a {@code CipherEngine} subclass that used for encryption.
 */
public class CipherEncryptEngine extends CipherEngine {

	private byte[] metadata = null;

	public CipherEncryptEngine(CipherMode mode, Function<byte[], Cipher> init, int metadataSize) {
		super(mode);
		metadata = new byte[metadataSize];
		this.c = init.apply(metadata);
	}
	
	@Override
	public byte[] update(byte[] buf, int off, int len) {
		if(finished) return null;
		if(metadata == null) {
			byte[] ret = c.update(buf, off, len);
			if(ret != null) return ret;
			else return new byte[0];
		} else {
			byte[] extra = c.update(buf, off, len);
			byte[] ret = new byte[metadata.length + extra.length];
			System.arraycopy(metadata, 0, ret, 0, metadata.length);
			System.arraycopy(extra, 0, ret, metadata.length, extra.length);
			metadata = null;
			return ret;
		}
	}
}
