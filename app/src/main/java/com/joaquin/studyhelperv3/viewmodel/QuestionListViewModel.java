package com.joaquin.studyhelperv3.viewmodel;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.joaquin.studyhelperv3.model.Question;
import com.joaquin.studyhelperv3.repo.StudyRepository;

import java.util.List;

public class QuestionListViewModel extends AndroidViewModel {
    private StudyRepository mStudyRepo;
    private final MutableLiveData<Long> mSubjectIdLiveData = new MutableLiveData<>();

    public LiveData<List<Question>> questionListLiveData =
            Transformations.switchMap(mSubjectIdLiveData, subjectId ->
                    mStudyRepo.getQuestions(subjectId));

    public QuestionListViewModel(@NonNull Application application) {
        super(application);
        mStudyRepo = StudyRepository.getInstance(application.getApplicationContext());
    }

    public void loadQuestions(long subjectId) {
        mSubjectIdLiveData.setValue(subjectId);
    }

    public void addQuestion(Question question) {
        mStudyRepo.addQuestion(question);
    }

    public void deleteQuestion(Question question) {
        mStudyRepo.deleteQuestion(question);
    }

}