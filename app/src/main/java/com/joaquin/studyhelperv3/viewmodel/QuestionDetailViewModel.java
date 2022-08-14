package com.joaquin.studyhelperv3.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.joaquin.studyhelperv3.model.Question;
import com.joaquin.studyhelperv3.repo.StudyRepository;

public class QuestionDetailViewModel extends AndroidViewModel {

    private StudyRepository mStudyRepo;
    private final MutableLiveData<Long> questionIdLiveData = new MutableLiveData<>();

    public LiveData<Question> questionLiveData =
            Transformations.switchMap(questionIdLiveData, questionId ->
                    mStudyRepo.getQuestion(questionId));

    public QuestionDetailViewModel(@NonNull Application application) {
        super(application);
        mStudyRepo = StudyRepository.getInstance(application.getApplicationContext());
    }

    public void loadQuestion(long questionId) {
        questionIdLiveData.setValue(questionId);
    }

    public void addQuestion(Question question) {
        mStudyRepo.addQuestion(question);
    }

    public void updateQuestion(Question question) {
        mStudyRepo.updateQuestion(question);
    }
}