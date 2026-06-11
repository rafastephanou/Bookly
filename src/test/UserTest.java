package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.User;

public class UserTest {

	@Test
	public void testConstructorWithExplicitId() {
		User user = new User(10, "Ana", "Silva", "ana@exemplo.com", "012345678-90", "SenhaSegura");

		assertEquals(10, user.getId());
		assertEquals("Ana", user.getName());
		assertEquals("Silva", user.getSurname());
		assertEquals("ana@exemplo.com", user.getEmail());
		assertEquals("012345678-90", user.getCpf());
		assertEquals("SenhaSegura", user.getPassword());
	}

	@Test
	public void testNewUserHasEmptyClubLists() {
		User user = new User(11, "Bruno", "Costa", "bruno@exemplo.com", "111", "pwd");

		assertNotNull(user.getJoinedBookClubs());
		assertNotNull(user.getCreatedBookClubs());
		assertTrue(user.getJoinedBookClubs().isEmpty());
		assertTrue(user.getCreatedBookClubs().isEmpty());
	}

	@Test
	public void testSetters() {
		User user = new User(12, "Carla", "Dias", "carla@exemplo.com", "222", "pwd");

		user.setName("Carlos");
		user.setSurname("Dantas");
		user.setEmail("carlos@exemplo.com");
		user.setCpf("333");
		user.setPassword("novaSenha");
		user.setId(99);

		assertEquals(99, user.getId());
		assertEquals("Carlos", user.getName());
		assertEquals("Dantas", user.getSurname());
		assertEquals("carlos@exemplo.com", user.getEmail());
		assertEquals("333", user.getCpf());
		assertEquals("novaSenha", user.getPassword());
	}

	@Test
	public void testToCsvLine() {
		User user = new User(7, "Ana", "Silva", "ana@exemplo.com", "012345678-90", "SenhaSegura");

		assertEquals("7,Ana,Silva,ana@exemplo.com,012345678-90,SenhaSegura", user.toCsvLine());
	}

	@Test
	public void testCounterIncrementsOnAutoIdConstructor() {
		int before = User.getNumUsersCreated();
		new User("Dora", "Lima", "dora@exemplo.com", "444", "pwd");
		assertEquals(before + 1, User.getNumUsersCreated());
	}
}
