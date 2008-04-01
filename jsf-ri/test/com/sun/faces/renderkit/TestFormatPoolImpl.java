/*
 * $Id: TestFormatPoolImpl.java,v 1.3 2002/08/09 21:01:50 eburns Exp $
 */

/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

// TestFormatPoolImpl.java

package com.sun.faces.renderkit;

import org.mozilla.util.Assert;
import org.mozilla.util.ParameterCheck;

import com.sun.faces.ServletFacesTestCase;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import com.sun.faces.RIConstants;

/**
 *
 *  <B>TestFormatPoolImpl</B> is a class ...
 *
 * <B>Lifetime And Scope</B> <P>
 *
 * @version $Id: TestFormatPoolImpl.java,v 1.3 2002/08/09 21:01:50 eburns Exp $
 * 
 * @see	Blah
 * @see	Bloo
 *
 */

public class TestFormatPoolImpl extends ServletFacesTestCase
{
//
// Protected Constants
//

//
// Class Variables
//

//
// Instance Variables
//

// Attribute Instance Variables

// Relationship Instance Variables

//
// Constructors and Initializers    
//

    public TestFormatPoolImpl() {super("TestFormatPoolImpl");}
    public TestFormatPoolImpl(String name) {super(name);}

//
// Class methods
//

//
// General Methods
//

    public void testDateFormat_format() {

	String result = null;
	FormatPool formatPool = new FormatPoolImpl();
	UIInput input = new UIInput();
	input.setComponentId("input");
	Date date = null;
	
	// formatStyle == short, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	try {
	    date = DateFormat.getDateInstance(DateFormat.SHORT, 
					      Locale.US).parse("12/31/52");
	}
	catch (ParseException e) {
	    assertTrue(false);
	}
	input.setAttribute("formatStyle", "SHORT");
	result = formatPool.dateFormat_format(getFacesContext(), input, date);
	assertTrue(null != result);
	assertTrue(result.equals("12/31/52"));

	// formatStyle == medium, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	try {
	    date = DateFormat.getDateInstance(DateFormat.MEDIUM, 
					      Locale.US).parse("Jan 12, 1952");
	}
	catch (ParseException e) {
	    assertTrue(false);
	}
	input.setAttribute("formatStyle", "MEDIUM");
	result = formatPool.dateFormat_format(getFacesContext(), input, date);
	assertTrue(null != result);
	assertTrue(result.equals("Jan 12, 1952"));

	// formatStyle == long, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	try {
	    date = DateFormat.getDateInstance(DateFormat.LONG, 
					      Locale.US).parse("January 12, 1952");
	}
	catch (ParseException e) {
	    assertTrue(false);
	}
	input.setAttribute("formatStyle", "LONG");
	result = formatPool.dateFormat_format(getFacesContext(), input, date);
	assertTrue(null != result);
	assertTrue(result.equals("January 12, 1952"));

	// formatStyle == full, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	try {
	    date = DateFormat.getDateInstance(DateFormat.FULL, 
					      Locale.US).parse("Saturday, April 12, 1952");
	}
	catch (ParseException e) {
	    assertTrue(false);
	}
	input.setAttribute("formatStyle", "FULL");
	result = formatPool.dateFormat_format(getFacesContext(), input, date);
	assertTrue(null != result);
	assertTrue(result.equals("Saturday, April 12, 1952"));

	// formatStyle == null, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	try {
	    date = DateFormat.getDateInstance(DateFormat.MEDIUM, 
					      Locale.US).parse("Apr 12, 1952");
	}
	catch (ParseException e) {
	    assertTrue(false);
	}
	input.setAttribute("formatStyle", null);
	result = formatPool.dateFormat_format(getFacesContext(), input, date);
	assertTrue(null != result);
	assertTrue(result.equals("Apr 12, 1952"));

    }

    public void testDateFormat_parse() {

	Date expectedResult = null, result = null;
	FormatPool formatPool = new FormatPoolImpl();
	UIInput input = new UIInput();
	input.setComponentId("input");
	String date = null;
	
	// formatStyle == short, timezone == null, get Locale from FacesContext
	try {
	    getFacesContext().setLocale(Locale.US);
	    date = "12/31/52";
	    input.setAttribute("formatStyle", "SHORT");
	    result = formatPool.dateFormat_parse(getFacesContext(), input, date);
	    assertTrue(null != result);
	    assertTrue(DateFormat.
		       getDateInstance(DateFormat.SHORT, 
				       Locale.US).parse(date).equals(result));
	}
	catch (Throwable e) {
	    assertTrue(false);
	}

	// formatStyle == medium, timezone == null, get Locale from FacesContext
	try {
	    getFacesContext().setLocale(Locale.US);
	    date = "Jan 12, 1952";
	    input.setAttribute("formatStyle", "MEDIUM");
	    result = formatPool.dateFormat_parse(getFacesContext(), input, date);
	    assertTrue(null != result);
	    assertTrue(DateFormat.
		       getDateInstance(DateFormat.MEDIUM, 
				       Locale.US).parse(date).equals(result));
	}
	catch (Throwable e) {
	    assertTrue(false);
	}

	// formatStyle == long, timezone == null, get Locale from FacesContext
	try {
	    getFacesContext().setLocale(Locale.US);
	    date = "January 12, 1952";
	    input.setAttribute("formatStyle", "LONG");
	    result = formatPool.dateFormat_parse(getFacesContext(), input, date);
	    assertTrue(null != result);
	    assertTrue(DateFormat.
		       getDateInstance(DateFormat.MEDIUM, 
				       Locale.US).parse(date).equals(result));
	}
	catch (Throwable e) {
	    assertTrue(false);
	}

	// formatStyle == full, timezone == null, get Locale from FacesContext
	try {
	    getFacesContext().setLocale(Locale.US);
	    date = "Saturday, April 12, 1952";
	    input.setAttribute("formatStyle", "FULL");
	    result = formatPool.dateFormat_parse(getFacesContext(), input, date);
	    assertTrue(null != result);
	    assertTrue(DateFormat.
		       getDateInstance(DateFormat.MEDIUM, 
				       Locale.US).parse("April 12, 1952").equals(result));
	}
	catch (Throwable e) {
	    assertTrue(false);
	}

    }

    public void testDateFormat_formatWithPattern() {
	String formatPattern = "EEE, MMM d, yyyy";
	String expectedDate = "Wed, Jul 10, 1996";
	String result = null;
	FormatPool formatPool = new FormatPoolImpl();
	UIInput input = new UIInput();
	input.setComponentId("input");
	Date date = null;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern,
								 Locale.US);
	
	// formatPattern == formatPattern, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	try {
	    date = simpleDateFormat.parse(expectedDate);
	}
	catch (ParseException e) {
	    assertTrue(false);
	}
	input.setAttribute("formatPattern", formatPattern);
	result = formatPool.dateFormat_format(getFacesContext(), input, date);
	assertTrue(null != result);
	assertTrue(result.equals(expectedDate));
    }

    public void testDateFormat_parseWithPattern() {
	String formatPattern = "EEE, MMM d, yyyy";
	String expectedDate = "Wed, Jul 10, 1996";

	Date expectedResult = null, result = null;
	FormatPool formatPool = new FormatPoolImpl();
	UIInput input = new UIInput();
	input.setComponentId("input");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern,
								 Locale.US);
	
	// formatStyle == short, timezone == null, get Locale from FacesContext
	try {
	    getFacesContext().setLocale(Locale.US);
	    input.setAttribute("formatPattern", formatPattern);
	    result = formatPool.dateFormat_parse(getFacesContext(), input, 
						 expectedDate);
	    assertTrue(null != result);
	    assertTrue(simpleDateFormat.parse(expectedDate).equals(result));
	}
	catch (Throwable e) {
	    assertTrue(false);
	}

    }

    /**

    * verify that two different UIComponent instances with
    * identical attribute sets return the same format instance,
    * demonstrating the hashMap is correctly used.

    */

    public void testDateFormat_hashMap() {
	UIInput input1, input2;
	DateFormat dateFormat1, dateFormat2;
	FormatPoolImpl formatPool = new FormatPoolImpl();


	input1 = new UIInput();
	input1.setComponentId("input1");
	input2 = new UIInput();
	input2.setComponentId("input2");

	// formatStyle == short, timezone == null, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	input1.setAttribute("formatStyle", "SHORT");
	input2.setAttribute("formatStyle", "SHORT");
	dateFormat1 = formatPool.getDateFormat(getFacesContext(), input1);
	dateFormat2 = formatPool.getDateFormat(getFacesContext(), input2);
	assertTrue(dateFormat1 == dateFormat2);

	// formatStyle == short, timezone == PST, get Locale from FacesContext
	getFacesContext().setLocale(Locale.US);
	input1.setAttribute("timezone", "PST");
	input2.setAttribute("timezone", "PST");
	dateFormat1 = formatPool.getDateFormat(getFacesContext(), input1);
	assertTrue(dateFormat1 != dateFormat2);
	dateFormat2 = formatPool.getDateFormat(getFacesContext(), input2);
	assertTrue(dateFormat1 == dateFormat2);

	// formatStyle == short, timezone == PST, get Locale from attribute

	LocalizationContext locCtx = 
	    new LocalizationContext(null, Locale.FRANCE);
	getFacesContext().getHttpSession().setAttribute("basicBundle", locCtx);
	
	input1.setAttribute("bundle", "basicBundle");
	input2.setAttribute("bundle", "basicBundle");
	dateFormat1 = formatPool.getDateFormat(getFacesContext(), input1);
	assertTrue(dateFormat1 != dateFormat2);
	dateFormat2 = formatPool.getDateFormat(getFacesContext(), input2);
	assertTrue(dateFormat1 == dateFormat2);
	
    }

    public void testFormatPoolInServletContext() {
	assertTrue(null !=
		   getFacesContext().getServletContext().
		   getAttribute(RIConstants.FORMAT_POOL));
    }



} // end of class TestFormatPoolImpl
