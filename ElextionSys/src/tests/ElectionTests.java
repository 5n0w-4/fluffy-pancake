package tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blocks.Elections;

class ElectionTests {
	Elections elections;
	
	public ElectionTests(Elections elections) {
		this.elections  = elections;
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void addCitizen() {
		assertEquals(true, elections.);
		fail("Not yet implemented");
	}

}
