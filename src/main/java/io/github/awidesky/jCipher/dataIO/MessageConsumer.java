/*	
 * Copyright (c) 2023 Eugene Hong
 *
 * This software is distributed under license. Use of this software
 * implies agreement with all terms and conditions of the accompanying
 * software license.
 * Please refer to LICENSE
 * */

package io.github.awidesky.jCipher.dataIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public interface MessageConsumer {

	/**
	 * Consume data(may be a plainText or cipherText) and writes to the destination(may be a <code>String</code>, <code>File</code>, <code>OutPutStream</code> etc.)
	 * 
	 * @param buffer the data
	 * @throws IOException 
	 * */
	public void consumeResult(byte[] buffer) throws IOException;
	

	public static MessageConsumer to(File f) throws FileNotFoundException {
		return to(new FileOutputStream(f));
	}
	public static MessageConsumer to(OutputStream os) {
		return new MessageConsumer() {
			private OutputStream out = os;
			@Override
			public void consumeResult(byte[] buffer) throws IOException {
				try {
					out.write(buffer);
				} catch (Exception e) {
					out.close();
					throw e;
				}
			}
		};
	}
	public static MessageConsumer to(WritableByteChannel ch) {
		return new MessageConsumer() {
			private WritableByteChannel out = ch;
			@Override
			public void consumeResult(byte[] buffer) throws IOException {
				try {
					ByteBuffer buf = ByteBuffer.wrap(buffer);
					while(buf.hasRemaining()) out.write(buf);
				} catch (Exception e) {
					out.close();
					throw e;
				}
			}
		};
	}
}
