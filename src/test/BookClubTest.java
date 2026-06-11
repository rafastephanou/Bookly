package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.model.BookClub;
import com.model.BookPoll;
import com.model.User;

public class BookClubTest {

	private User newCreator(int id) {
		return new User(id, "Lia", "Moraes", "lia@exemplo.com", "012345678-90", "pwd");
	}

	@Test
	public void testCreationWithExplicitId() {
		User creator = newCreator(5);
		BookClub club = new BookClub(1, creator, "Clube da Lia");

		assertEquals(1, club.getId());
		assertSame(creator, club.getCreator());
		assertEquals("Clube da Lia", club.getName());
	}

	@Test
	public void testNewClubHasEmptyCollections() {
		BookClub club = new BookClub(2, newCreator(6), "Clube Vazio");

		assertTrue(club.getParticipants().isEmpty());
		assertTrue(club.getPolls().isEmpty());
		assertTrue(club.getMeetings().isEmpty());
	}

	@Test
	public void testSetName() {
		BookClub club = new BookClub(3, newCreator(7), "Nome Antigo");
		club.setName("Nome Novo");
		assertEquals("Nome Novo", club.getName());
	}

	@Test
	public void testAddPollRegistersPollAndBackReference() {
		BookClub club = new BookClub(4, newCreator(8), "Clube com Votacao");
		ArrayList<String> options = new ArrayList<>(Arrays.asList("Livro A", "Livro B"));
		BookPoll poll = new BookPoll(100, club, "Qual livro?", options, new int[options.size()]);

		club.addPoll(poll);

		assertEquals(1, club.getPolls().size());
		assertTrue(club.getPolls().contains(poll));
		assertSame(club, poll.getBookClub());
	}

	@Test
	public void testAddPollIgnoresDuplicates() {
		BookClub club = new BookClub(5, newCreator(9), "Clube");
		ArrayList<String> options = new ArrayList<>(Arrays.asList("A", "B"));
		BookPoll poll = new BookPoll(101, club, "Pergunta", options, new int[options.size()]);

		club.addPoll(poll);
		club.addPoll(poll);

		assertEquals(1, club.getPolls().size());
	}

	@Test
	public void testToCsvLineWithEmptyCollections() {
		BookClub club = new BookClub(8, newCreator(13), "Clube");

		// id,creatorId,name,participantes,polls,meetings (tres ultimos vazios)
		assertEquals("8,13,Clube,,,", club.toCsvLine());
	}
}
