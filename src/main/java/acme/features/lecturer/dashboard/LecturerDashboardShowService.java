/*
 * LecturerDashboardShowService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.LecturerDashboard;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerDashboardShowService extends AbstractService<Lecturer, LecturerDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Principal principal;
		principal = super.getRequest().getPrincipal();
		final int lecturerId = principal.getActiveRoleId();

		LecturerDashboard dashboard;
		Integer totalNumberOfTheoryLectures;
		Integer totalNumberOfHandsOnLectures;
		Double averageLearningTimePerLecture;
		Double deviationLearningTimePerLecture;
		Double minimumLearningTimePerLecture;
		Double maximumLearningTimePerLecture;
		Double averageLearningTimePerCourse;
		Double deviationLearningTimePerCourse;
		Double minimumLearningTimePerCourse;
		Double maximumLearningTimePerCourse;

		totalNumberOfTheoryLectures = this.repository.totalNumberOfTheoryLectures(lecturerId);
		totalNumberOfHandsOnLectures = this.repository.totalNumberOfHandsOnLectures(lecturerId);
		averageLearningTimePerLecture = this.repository.averageLearningTimePerLecture(lecturerId);
		deviationLearningTimePerLecture = this.repository.deviationLearningTimePerLecture(lecturerId);
		minimumLearningTimePerLecture = this.repository.minimumLearningTimePerLecture(lecturerId);
		maximumLearningTimePerLecture = this.repository.maximumLearningTimePerLecture(lecturerId);
		averageLearningTimePerCourse = this.repository.averageLearningTimePerCourse(lecturerId);
		deviationLearningTimePerCourse = this.repository.deviationLearningTimePerCourse(lecturerId);
		minimumLearningTimePerCourse = this.repository.minimumLearningTimePerCourse(lecturerId);
		maximumLearningTimePerCourse = this.repository.maximumLearningTimePerCourse(lecturerId);

		dashboard = new LecturerDashboard();
		dashboard.setTotalNumberOfTheoryLectures(totalNumberOfTheoryLectures);
		dashboard.setTotalNumberOfHandsOnLectures(totalNumberOfHandsOnLectures);
		dashboard.setAverageLearningTimePerLecture(averageLearningTimePerLecture);
		dashboard.setDeviationLearningTimePerLecture(deviationLearningTimePerLecture);
		dashboard.setMinimumLearningTimePerLecture(minimumLearningTimePerLecture);
		dashboard.setMaximumLearningTimePerLecture(maximumLearningTimePerLecture);
		dashboard.setAverageLearningTimePerCourse(averageLearningTimePerCourse);
		dashboard.setDeviationLearningTimePerCourse(deviationLearningTimePerCourse);
		dashboard.setMinimumLearningTimePerCourse(minimumLearningTimePerCourse);
		dashboard.setMaximumLearningTimePerCourse(maximumLearningTimePerCourse);

		super.getBuffer().setData(dashboard);
	}

	@Override
	public void unbind(final LecturerDashboard object) {
		final Tuple tuple;

		tuple = super.unbind(object, //
			"totalNumberOfTheoryLectures", "totalNumberOfHandsOnLectures",//
			"averageLearningTimePerLecture", "deviationLearningTimePerLecture",//
			"minimumLearningTimePerLecture", "maximumLearningTimePerLecture",//
			"averageLearningTimePerCourse", "deviationLearningTimePerCourse",//
			"minimumLearningTimePerCourse", "maximumLearningTimePerCourse");

		super.getResponse().setData(tuple);
	}

}
