package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long ID = new Long(1L);
    private static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand notesToNotesCommand;

    @Before
    public void setUp() throws Exception {
        notesToNotesCommand = new NotesToNotesCommand();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(notesToNotesCommand.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);
        //when
        NotesCommand notesCommand = notesToNotesCommand.convert(notes);
        //then
        assertEquals(ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}