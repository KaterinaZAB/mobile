package ru.mirea.zubarevaes.mireaproject.ui.taskInFile;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.zubarevaes.mireaproject.R;
import ru.mirea.zubarevaes.mireaproject.databinding.FragmentFileBinding;

public class FileFragment extends Fragment {

    private FragmentFileBinding fragmentFileBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentFileBinding = FragmentFileBinding.inflate(inflater, container, false);
        return fragmentFileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // установка обработчика нажатия на кнопку
        fragmentFileBinding.fabAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertFileToDocx();
            }
        });
    }

    private void convertFileToDocx() {
        List<String> lines = new ArrayList<>();
        try {
            // открытие файла file1.txt из папки raw
            InputStream inputStream = getResources().openRawResource(R.raw.file1);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            Log.w("RawFile", String.format("Read from file successful: %s", lines.toString()));
            if (createDocx(lines)) {
                // получение пути к файлу
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(path, "file1.docx");
                // вывод сообщения об успешной конвертации файла
                Toast.makeText(requireContext(), "Файл успешно сконвертирован и сохранен в " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Ошибка при конвертации файла", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.w("RawFile", String.format("Read from file failed: %s", e.getMessage()));
        }
    }

    private boolean createDocx(List<String> lines) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "file1.docx");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            for (String line : lines) {
                run.setText(line);
                run.addBreak();
            }

            document.write(fileOutputStream);
            fileOutputStream.close();
            document.close();
            return true; // возвращаю true в случае успешной конвертации файла

        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + file, e);
            return false; // возвращаю false в случае ошибки при конвертации файла
        }
    }
}
