package com.joaquin.studyhelperv3.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.joaquin.studyhelperv3.model.Question;
import com.joaquin.studyhelperv3.model.Subject;

@Database(entities = {Question.class, Subject.class}, version = 1)
public abstract class StudyDatabase extends RoomDatabase {

    public abstract QuestionDao questionDao();
    public abstract SubjectDao subjectDao();
}