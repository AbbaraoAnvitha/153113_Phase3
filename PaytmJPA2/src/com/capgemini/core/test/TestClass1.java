package com.capgemini.core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Wallet;
import com.capgemini.core.exception.InsufficientBalanceException;
import com.capgemini.core.exception.InvalidInputException;
import com.capgemini.core.service.WalletService;
import com.capgemini.core.service.WalletServiceImpl;

public class TestClass1 {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount11() throws InvalidInputException 
	{
		walletService.createAccount(null, "9177640926", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount12() throws InvalidInputException 
	{
		walletService.createAccount("", "9550045666", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount13() throws InvalidInputException 
	{
		walletService.createAccount("Anvitha", "9177", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount14() throws InvalidInputException 
	{
		walletService.createAccount("Anvitha", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount15() throws InvalidInputException 
	{
		walletService.createAccount("", "", new BigDecimal(1500));
	}
	
	
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber1() throws InvalidInputException, InsufficientBalanceException {
		String name = "Anvitha";
		String mobileNumber = "70951346163";
		BigDecimal balance = new BigDecimal(3000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}

	@Test(expected=NullPointerException.class)
	public void testCreateAccount1() throws InvalidInputException{
		walletService.createAccount(null, null, null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() throws InvalidInputException {
		Customer customer = new Customer();
		Wallet wallet = new Wallet();
		String name = "Anvitha";
		String number = "917764";
		BigDecimal amount = new BigDecimal(5000);
		customer.setName(name);
		customer.setMobileNo(number);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		customer = walletService.createAccount(name, number, amount);
		assertNotEquals(null, customer);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() throws InvalidInputException{
		String name = "Anvitha";
		String mobileno = "90045666";
		BigDecimal amount = new BigDecimal(0);
		Customer customer = walletService.createAccount(name, mobileno, amount);
		assertNotEquals(null,customer);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() throws InvalidInputException{
		String name = "Anvitha";
		String mobileno = "9177";
		BigDecimal amount = new BigDecimal(10000);
		Customer customer = walletService.createAccount(name, mobileno, amount);
		assertNotEquals(null,customer);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() throws InvalidInputException{
		String name = "Anvitha";
		String mobileno = "9177640926";
		BigDecimal amount = new BigDecimal(-1000);
		Customer customer = walletService.createAccount(name, mobileno, amount);
		assertNotEquals(null,customer);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testshowBalance1() throws InvalidInputException{
		String number = "9177640926";
		Customer customer = walletService.showBalance(number);
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testWithdraw() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "917764";
		BigDecimal amount = new BigDecimal(3000);
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testWithdraw1() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "917764";
		BigDecimal amount = new BigDecimal(-3000);
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test(expected=NullPointerException.class)
	public void testWithdraw2() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount(null, null);
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw3() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount("900000000", new BigDecimal(2000));
	}
	
	@Test(expected=NullPointerException.class)
	public void testWithdraw4() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount(null, new BigDecimal(2000));
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw5() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "9177640926";
		BigDecimal amount = new BigDecimal(-3000);
		walletService.withdrawAmount(mobileNumber, amount);
	}	
	@Test(expected=InvalidInputException.class)
	public void testDeposit() throws InvalidInputException,InsufficientBalanceException {
		String mobileNumber = "9177609";
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		System.out.println(customer1.getWallet().getBalance());
		assertEquals(8000 , customer1.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit1() throws InvalidInputException
	{
		walletService.depositAmount("900000000", new BigDecimal(2000));
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit2() throws InvalidInputException,InsufficientBalanceException
	{
		Customer customer=walletService.depositAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=customer.getWallet().getBalance();
		assertEquals(null, actual);
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit3() throws InvalidInputException
	{
		walletService.depositAmount(null, new BigDecimal(2000));
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit4() throws InvalidInputException
	{
		walletService.depositAmount(null, null);
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit5() throws InvalidInputException,InsufficientBalanceException
	{
		Customer customer=walletService.depositAmount("9177640926", new BigDecimal(2000));
		BigDecimal actual=customer.getWallet().getBalance();
		assertEquals(null, actual);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "Anvitha";
		String mobileNumber = "91776409967";
		BigDecimal balance = new BigDecimal(3000);
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		assertEquals(null, customer);
		
	}


}
