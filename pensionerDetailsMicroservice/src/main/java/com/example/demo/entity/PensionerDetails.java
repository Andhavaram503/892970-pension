package com.example.demo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PensionerDetails {

	private String name;

	private Date dateOfBirth;

	private String pan;

	private double salaryEarned;

	private double allowances;

	private String pensionType;

	private Bank bank;

}
