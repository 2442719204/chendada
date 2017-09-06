package org.lanqiao.util;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

public class QQEmailUtilTest {

	@Test
	public void test() {
		try {
			try {
				QQEmailUtil.sendMail("2442719204@qq.com", "哎呦喂");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
