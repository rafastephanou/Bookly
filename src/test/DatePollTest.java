package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.model.BookClub;
import com.model.DatePoll;
import com.model.User;

public class DatePollTest {

	private DatePoll newPoll(String... options) {
		User creator = new User(30, "Sara", "Nunes", "sara@exemplo.com", "222", "pwd");
		BookClub club = new BookClub(60, creator, "Clube de Datas");
		ArrayList<String> opts = new ArrayList<>(Arrays.asList(options));
		return new DatePoll(300, club, "Qual data?", opts, new int[opts.size()]);
	}

	@Test
	public void testInitialVotesAreZero() {
		DatePoll poll = newPoll("12/06", "13/06");

		assertArrayEquals(new int[] {0, 0}, poll.getVotes());
		assertEquals("0,0", poll.getVotesAsCSV());
	}

	@Test
	public void testRegisterVoteIncrementsOption() {
		DatePoll poll = newPoll("12/06", "13/06", "14/06");

		poll.registerVote(0);
		poll.registerVote(2);
		poll.registerVote(2);

		assertArrayEquals(new int[] {1, 0, 2}, poll.getVotes());
	}

	@Test
	public void testRegisterVoteOutOfRangeIsIgnored() {
		DatePoll poll = newPoll("12/06", "13/06");

		poll.registerVote(99);

		assertArrayEquals(new int[] {0, 0}, poll.getVotes());
	}

	@Test
	public void testTypeIsDate() {
		assertEquals("DATE", newPoll("12/06", "13/06").getType());
	}

	@Test
	public void testQuestionAndOptions() {
		DatePoll poll = newPoll("12/06", "13/06");

		assertEquals("Qual data?", poll.getQuestion());
		assertEquals(2, poll.getOptions().size());
	}
}
