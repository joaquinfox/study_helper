package com.joaquin.studyhelperv3;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.joaquin.studyhelperv3.model.Question;
import com.joaquin.studyhelperv3.model.Subject;
import com.joaquin.studyhelperv3.viewmodel.QuestionListViewModel;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    public static final String EXTRA_SUBJECT_ID = "com.zybooks.studyhelper.subject_id";
    public static final String EXTRA_SUBJECT_TEXT  = "com.zybooks.studyhelper.subject_text";

    private QuestionListViewModel mQuestionListViewModel;
    private Subject mSubject;
    private List<Question> mQuestionList;
    private TextView mAnswerLabelTextView;
    private TextView mAnswerTextView;
    private Button mAnswerButton;
    private TextView mQuestionTextView;
    private ViewGroup mShowQuestionLayout;
    private ViewGroup mNoQuestionLayout;
    private int mCurrentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mAnswerLabelTextView = findViewById(R.id.answer_label_text_view);
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mAnswerButton = findViewById(R.id.answer_button);
        mShowQuestionLayout = findViewById(R.id.show_question_layout);
        mNoQuestionLayout = findViewById(R.id.no_question_layout);

        // Add click callbacks
        mAnswerButton.setOnClickListener(view -> toggleAnswerVisibility());
        findViewById(R.id.add_question_button).setOnClickListener(view -> addQuestion());

        // SubjectActivity should provide the subject ID and text
        Intent intent = getIntent();
        long subjectId = intent.getLongExtra(EXTRA_SUBJECT_ID, 0);
        String subjectText = intent.getStringExtra(EXTRA_SUBJECT_TEXT);
        mSubject = new Subject(subjectText);
        mSubject.setId(subjectId);

        // Get all questions for this subject
        mQuestionListViewModel = new QuestionListViewModel(getApplication());
        mQuestionList = mQuestionListViewModel.getQuestions(subjectId);

        // Display question
        updateUI();
    }

    private void updateUI() {
        showQuestion(mCurrentQuestionIndex);

        if (mQuestionList.isEmpty()) {
            updateAppBarTitle();
            displayQuestion(false);
        } else {
            displayQuestion(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //  Determine which app bar item was chosen
        if (item.getItemId() == R.id.previous) {
            showQuestion(mCurrentQuestionIndex - 1);
            return true;
        }
        else if (item.getItemId() == R.id.next) {
            showQuestion(mCurrentQuestionIndex + 1);
            return true;
        }
        else if (item.getItemId() == R.id.add) {
            addQuestion();
            return true;
        }
        else if (item.getItemId() == R.id.edit) {
            editQuestion();
            return true;
        }
        else if (item.getItemId() == R.id.delete) {
            deleteQuestion();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayQuestion(boolean display) {
        if (display) {
            mShowQuestionLayout.setVisibility(View.VISIBLE);
            mNoQuestionLayout.setVisibility(View.GONE);
        }
        else {
            mShowQuestionLayout.setVisibility(View.GONE);
            mNoQuestionLayout.setVisibility(View.VISIBLE);
        }
    }

    private void updateAppBarTitle() {

        // Display subject and number of questions in app bar
        String title = getResources().getString(R.string.question_number,
                mSubject.getText(), mCurrentQuestionIndex + 1, mQuestionList.size());
        setTitle(title);
    }

    private void addQuestion() {
        // TODO: Add question
    }

    private void editQuestion() {
        // TODO: Edit question
    }

    private void deleteQuestion() {
        // TODO: Delete question
    }

    private void showQuestion(int questionIndex) {

        // Show question at the given index
        if (mQuestionList.size() > 0) {
            if (questionIndex < 0) {
                questionIndex = mQuestionList.size() - 1;
            }
            else if (questionIndex >= mQuestionList.size()) {
                questionIndex = 0;
            }

            mCurrentQuestionIndex = questionIndex;
            updateAppBarTitle();

            Question question = mQuestionList.get(mCurrentQuestionIndex);
            mQuestionTextView.setText(question.getText());
            mAnswerTextView.setText(question.getAnswer());
        }
        else {
            // No questions yet
            mCurrentQuestionIndex = -1;
        }
    }

    private void toggleAnswerVisibility() {
        if (mAnswerTextView.getVisibility() == View.VISIBLE) {
            mAnswerButton.setText(R.string.show_answer);
            mAnswerTextView.setVisibility(View.INVISIBLE);
            mAnswerLabelTextView.setVisibility(View.INVISIBLE);
        }
        else {
            mAnswerButton.setText(R.string.hide_answer);
            mAnswerTextView.setVisibility(View.VISIBLE);
            mAnswerLabelTextView.setVisibility(View.VISIBLE);
        }
    }
}

