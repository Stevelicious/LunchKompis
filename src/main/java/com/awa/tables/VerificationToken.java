package com.awa.tables;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Steven Hu on 2016-10-25.
 */
//@Entity
//public class VerificationToken {
//	private static final int EXPIRATION = 60 * 24;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	private String token;
//
//	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//	@JoinColumn(nullable = false, name = "user_id")
//	private User user;
//
//	private LocalDateTime expiryDate;
//
//	public VerificationToken() {
//		super();
//	}
//	public VerificationToken(String token, User user) {
//		super();
//		this.token = token;
//		this.user = user;
//		this.expiryDate = calculateExpiryDate(EXPIRATION);
//		this.status = 1;
//	}
//
//	private Date calculateExpiryDate(int expiryTimeInMinutes) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Timestamp(cal.getTime().getTime()));
//		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
//		return new Date(cal.getTime().getTime());
//	}
//
//	// standard getters and setters
//}
