package eTargeting.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListsModelTest {

	protected ListsModel listsModel;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNumberOfLists() throws Exception {
		listsModel = new ListsModel();
		assertEquals(35, listsModel.numberOfLists(1));
	}
	
	@Test
	public void testCheckSubscribersBelonging() throws Exception {
		listsModel = new ListsModel();
		listsModel.setOwner(1);
		assertEquals(true, listsModel.checkSubscribersBelonging(new int[]{1,2,3}));
	}

}
