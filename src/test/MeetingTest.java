package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.model.BookClub;
import com.model.Meeting;
import com.model.User;

public class MeetingTest {

	private BookClub newClub(User creator) {
		return new BookClub(70, creator, "Clube de Encontros");
	}

	@Test
	public void testConstructor() {
		User creator = new User(40, "Theo", "Reis", "theo@exemplo.com", "111", "pwd");
		BookClub club = newClub(creator);
		Date date = new Date();

		Meeting meeting = new Meeting(0, club, "Presencial", date, "Biblioteca Central");

		assertEquals(1, meeting.getId());
		assertEquals("Presencial", meeting.getType());
		assertEquals(date, meeting.getDate());
		assertEquals("Biblioteca Central", meeting.getLocation());
		assertSame(club, meeting.getBookClub());
	}

	@Test
	public void testCreatorComesFromBookClub() {
		User creator = new User(41, "Vera", "Pinto", "vera@exemplo.com", "222", "pwd");
		BookClub club = newClub(creator);

		Meeting meeting = new Meeting(0, club, "Online", new Date(), "Google Meet");

		assertSame(creator, meeting.getCreator());
		assertSame(club.getCreator(), meeting.getCreator());
	}

	@Test
	public void testNewMeetingHasNoParticipants() {
		User creator = new User(42, "Iris", "Gomes", "iris@exemplo.com", "333", "pwd");
		Meeting meeting = new Meeting(0, newClub(creator), "Presencial", new Date(), "Cafe");

		assertNotNull(meeting.getParticipants());
		assertTrue(meeting.getParticipants().isEmpty());
	}

	@Test
	public void testIdDerivesFromConstructorArgument() {
		User creator = new User(43, "Noa", "Dias", "noa@exemplo.com", "444", "pwd");
		Meeting meeting = new Meeting(4, newClub(creator), "Online", new Date(), "Zoom");

		// id = argumento + 1
		assertEquals(5, meeting.getId());
	}
}
