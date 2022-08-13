package com.joaquin.studyhelperv3.repo;


import android.content.Context;

import androidx.room.Room;

import com.joaquin.studyhelperv3.model.Question;
import com.joaquin.studyhelperv3.model.Subject;
import java.util.List;

public class StudyRepository {

    private static StudyRepository mStudyRepo;
    private final SubjectDao mSubjectDao;
    private final QuestionDao mQuestionDao;

    public static StudyRepository getInstance(Context context) {
        if (mStudyRepo == null) {
            mStudyRepo = new StudyRepository(context);
        }
        return mStudyRepo;
    }

    private StudyRepository(Context context) {
        StudyDatabase database = Room.databaseBuilder(context, StudyDatabase.class, "study.db")
                .allowMainThreadQueries()
                .build();

        mSubjectDao = database.subjectDao();
        mQuestionDao = database.questionDao();

        if (mSubjectDao.getSubjects().isEmpty()) {
            addStarterData();
        }
    }

    private void addStarterData() {
        Subject subject = new Subject("Math");
        long subjectId = mSubjectDao.addSubject(subject);

        Question question = new Question();
        question.setText("What is 2 + 3?");
        question.setAnswer("2 + 3 = 5");
        question.setSubjectId(subjectId);
        mQuestionDao.addQuestion(question);

        question = new Question();
        question.setText("What is pi?");
        question.setAnswer("The ratio of a circle's circumference to its diameter.");
        question.setSubjectId(subjectId);
        mQuestionDao.addQuestion(question);

        subject = new Subject("History");
        subjectId = mSubjectDao.addSubject(subject);

        question = new Question();
        question.setText("On what date was the U.S. Declaration of Independence adopted?");
        question.setAnswer("July 4, 1776");
        question.setSubjectId(subjectId);
        mQuestionDao.addQuestion(question);

        subject = new Subject("Computing");
        mSubjectDao.addSubject(subject);
    }

    public void addSubject(Subject subject) {
        long subjectId = mSubjectDao.addSubject(subject);
        subject.setId(subjectId);
    }

    public Subject getSubject(long subjectId) {
        return mSubjectDao.getSubject(subjectId);
    }

    public List<Subject> getSubjects() {
        return mSubjectDao.getSubjects();
    }

    public void deleteSubject(Subject subject) {
        mSubjectDao.deleteSubject(subject);
    }

    public Question getQuestion(long questionId) {
        return mQuestionDao.getQuestion(questionId);
    }

    public List<Question> getQuestions(long subjectId) {
        return mQuestionDao.getQuestions(subjectId);
    }

    public void addQuestion(Question question) {
        long questionId = mQuestionDao.addQuestion(question);
        question.setId(questionId);
    }

    public void updateQuestion(Question question) {
        mQuestionDao.updateQuestion(question);
    }

    public void deleteQuestion(Question question) {
        mQuestionDao.deleteQuestion(question);
    }
}

