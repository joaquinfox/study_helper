package com.joaquin.studyhelperv3.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.joaquin.studyhelperv3.model.Subject;
import com.joaquin.studyhelperv3.repo.StudyRepository;
import java.util.List;

public class SubjectListViewModel extends AndroidViewModel {

    private final StudyRepository mStudyRepo;

    public SubjectListViewModel(Application application) {
        super(application);
        mStudyRepo = StudyRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Subject>> getSubjects() {
        return mStudyRepo.getSubjects();
    }

    public void addSubject(Subject subject) {
        mStudyRepo.addSubject(subject);
    }

    public void deleteSubject(Subject subject) {
        mStudyRepo.deleteSubject(subject);
    }
}