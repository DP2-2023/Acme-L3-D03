
package acme.features.authenticated.notes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.notes.Note;
import acme.framework.components.accounts.Authenticated;
import acme.framework.controllers.AbstractController;

@Controller
public class AuthenticatedNotesController extends AbstractController<Authenticated, Note> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedNotesListService		notesService;

	@Autowired
	protected AuthenticatedNotesShowService		showNotesService;

	@Autowired
	protected AuthenticatedNotesCreateService	createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.notesService);
		super.addBasicCommand("show", this.showNotesService);
		super.addBasicCommand("create", this.createService);
	}
}
