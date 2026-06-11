package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.model.BookClub;
import com.model.BookPoll;
import com.model.User;

public class BookPollTest {

	private BookClub newClub() {
		User creator = new User(20, "Rui", "Alves", "rui@exemplo.com", "111", "pwd");
		return new BookClub(50, creator, "Clube de Teste");
	}

	private BookPoll newPoll(String... options) {
		ArrayList<String> opts = new ArrayList<>(Arrays.asList(options));
		return new BookPoll(200, newClub(), "Qual livro vamos ler?", opts, new int[opts.size()]);
	}

	@Test
	public void testInitialVotesAreZero() {
		BookPoll poll = newPoll("Livro A", "Livro B", "Livro C");

		assertArrayEquals(new int[] {0, 0, 0}, poll.getVotes());
		assertEquals("0,0,0", poll.getVotesAsCSV());
	}

	@Test
	public void testOptionsArePreserved() {
		BookPoll poll = newPoll("Livro A", "Livro B");

		assertEquals(2, poll.getOptions().size());
		assertEquals("Livro A", poll.getOptions().get(0));
		assertEquals("Livro B", poll.getOptions().get(1));
		assertEquals("Qual livro vamos ler?", poll.getQuestion());
	}

	@Test
	public void testRegisterVoteIncrementsOption() {
		BookPoll poll = newPoll("Livro A", "Livro B", "Livro C");

		poll.registerVote(1);
		poll.registerVote(1);
		poll.registerVote(2);

		assertArrayEquals(new int[] {0, 2, 1}, poll.getVotes());
		assertEquals("0,2,1", poll.getVotesAsCSV());
	}

	@Test
	public void testRegisterVoteOutOfRangeIsIgnored() {
		BookPoll poll = newPoll("Livro A", "Livro B");

		poll.registerVote(-1);
		poll.registerVote(5);

		assertArrayEquals(new int[] {0, 0}, poll.getVotes());
	}

	@Test
	public void testTypeIsBook() {
		assertEquals("BOOK", newPoll("A", "B").getType());
	}

	@Test
	public void testBookClubBackReference() {
		BookClub club = newClub();
		ArrayList<String> opts = new ArrayList<>(Arrays.asList("A", "B"));
		BookPoll poll = new BookPoll(201, club, "Pergunta", opts, new int[opts.size()]);

		assertSame(club, poll.getBookClub());
		assertEquals(201, poll.getId());
	}
}
