package io.github.awidesky.jCipher.metadata;

public class IVCipherProperty extends CipherProperty {
	
	/**
	 * Size of nonce(Initial Vector) in bytes
	 * */
	public final int NONCESIZE;
	
	/**
	 * Initialize <code>CipherProperty</code> with given parameters.
	 *
	 * @param algorithmName Name of the cipher algorithm, like <code>AES</code> or <code>ChaCha20-Poly1305</code>
	 * @param algorithmMode Mode of the cipher algorithm, like <code>CBC</code> or <code>GCM</code>
	 * @param algorithmPadding Padding of the cipher algorithm, like <code>NoPadding</code> or <code>PKCS5Padding</code>
	 * @param keyAlgorithmName Name of the key algorithm, like <code>AES</code> or <code>ChaCha20</code>
	 * @param nonceSize Size of nonce(Initial Vector) in bytes
	 */
	public IVCipherProperty(String algorithmName, String algorithmMode, String algorithmPadding, String keyAlgorithmName, int nonceSize) {
		super(algorithmName, algorithmMode, algorithmPadding, keyAlgorithmName);
		NONCESIZE = nonceSize;
	}

	@Override
	protected String fields() {
		return super.fields() + ", NONCESIZE=" + NONCESIZE + "byte";
	}
	
}
