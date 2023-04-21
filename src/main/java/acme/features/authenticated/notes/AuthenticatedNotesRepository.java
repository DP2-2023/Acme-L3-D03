
package acme.features.authenticated.notes;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.notes.Note;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedNotesRepository extends AbstractRepository {

	@Query("SELECT n FROM Note n WHERE n.id = :id")
	Note findNoteById(int id);

	@Query("SELECT n from Note n")
	Collection<Note> findAllNotes();

	@Query("SELECT n from Note n WHERE n.moment >= :deadline")
	Collection<Note> findRecentNotes(Date deadline);

}
