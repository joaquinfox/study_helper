package com.joaquin.studyhelperv3.repo;
import androidx.room.*;
import com.joaquin.studyhelperv3.model.Question;
import java.util.List;
import androidx.lifecycle.LiveData;
@Dao
public interface QuestionDao {
    @Query("SELECT * FROM Question WHERE id = :id")
    LiveData<Question> getQuestion(long id);

    @Query("SELECT * FROM Question WHERE subject_id = :subjectId ORDER BY id")
    LiveData<List<Question>> getQuestions(long subjectId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addQuestion(Question question);

    @Update
    void updateQuestion(Question question);

    @Delete
    void deleteQuestion(Question question);
}