package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;

@Service
public class MfaService {

	public boolean check(String hexKey, String code) {
		try {
			return TimeBasedOneTimePasswordUtil.validateCurrentNumberHex(hexKey, Integer.parseInt(code), 10000);
		} catch (GeneralSecurityException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
}