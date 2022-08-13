package com.joaquin.studyhelperv3.viewmodel;


import android.app.Application;
import com.joaquin.studyhelperv3.model.Subject;
import com.joaquin.studyhelperv3.repo.StudyRepository;
import java.util.List;

public class SubjectListViewModel {

    private StudyRepository studyRepo;

    public SubjectListViewModel(Application application) {
        studyRepo = StudyRepository.getInstance(application.getApplicationContext());
    }

    public List<Subject> getSubjects() {
        return studyRepo.getSubjects();
    }

    public void addSubject(Subject subject) {
        studyRepo.addSubject(subject);
    }
}