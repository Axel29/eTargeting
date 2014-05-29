package eTargeting.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListsModelTest {

	protected ListsModel listsModel = new ListsModel();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNumberOfLists() throws Exception {
		assertEquals(35, listsModel.numberOfLists(1));
	}
	
	@Test
	public void testCheckSubscribersBelonging() throws Exception {
		listsModel.setOwner(1);
		assertEquals(true, listsModel.checkSubscribersBelonging(new int[]{1,2,3}));
	}
}
