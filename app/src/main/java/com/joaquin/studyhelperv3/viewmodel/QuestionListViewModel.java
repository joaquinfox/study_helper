package com.joaquin.studyhelperv3.viewmodel;


import android.app.Application;
import com.joaquin.studyhelperv3.model.Question;
import com.joaquin.studyhelperv3.repo.StudyRepository;
import java.util.List;

public class QuestionListViewModel {

    private StudyRepository studyRepo;

    public QuestionListViewModel(Application application) {
        studyRepo = StudyRepository.getInstance(application.getApplicationContext());
    }

    public List<Question> getQuestions(long subjectId) {
        return studyRepo.getQuestions(subjectId);
    }
}