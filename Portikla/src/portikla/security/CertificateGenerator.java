package portikla.security;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class CertificateGenerator {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private static final String CONTENT_SIGNER_ALGORITHM = "SHA1WithRSAEncryption";
	private static final String PROVIDER = "BC";
	private static final String ASYMMETRIC_ALGORITHM = "RSA";

	public X509Certificate generateCertificate(IssuerData issuerData,
			SubjectData subjectData) {
		try {
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder(
					CONTENT_SIGNER_ALGORITHM);
			builder = builder.setProvider(PROVIDER);

			ContentSigner contentSigner = builder.build(issuerData
					.getPrivateKey());

			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
					issuerData.getX500name(), new BigInteger(
							subjectData.getSerialNumber()),
					subjectData.getStartDate(), subjectData.getEndDate(),
					subjectData.getX500name(), subjectData.getPublicKey());

			X509CertificateHolder certHolder = certGen.build(contentSigner);

			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider(PROVIDER);

			return certConverter.getCertificate(certHolder);
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return null;
		} catch (OperatorCreationException e) {
			e.printStackTrace();
			return null;
		} catch (CertificateException e) {
			e.printStackTrace();
			return null;
		}
	}

	public KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator
					.getInstance(ASYMMETRIC_ALGORITHM);
			keyGen.initialize(1024);

			KeyPair pair = keyGen.generateKeyPair();

			return pair;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public X509Certificate generateSignedCertificate(X500Name issuerX500Name,
			PrivateKey caPrivateKey, X500Name subjectX500Name,
			KeyPair subjectKeyPair, int daysValid, String serialNumber) {
		X509Certificate cert = null;

		Date startDate = new Date();
		Date endDate;

		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, daysValid);

		endDate = c.getTime();

		IssuerData issuerData = new IssuerData(caPrivateKey, issuerX500Name);
		SubjectData subjectData = new SubjectData(subjectKeyPair.getPublic(),
				subjectX500Name, serialNumber, startDate, endDate);

		cert = generateCertificate(issuerData, subjectData);

		return cert;
	}

	public X509Certificate generateSelfSignedCertificate(X500Name x500Name,
			KeyPair keyPair, int daysValid, String serialNumber) {
		X509Certificate cert = null;

		Date startDate = new Date();
		Date endDate;

		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, daysValid);

		endDate = c.getTime();

		IssuerData issuerData = new IssuerData(keyPair.getPrivate(), x500Name);
		SubjectData subjectData = new SubjectData(keyPair.getPublic(),
				x500Name, serialNumber, startDate, endDate);

		cert = generateCertificate(issuerData, subjectData);

		return cert;
	}
}
