package ru.mirea.zubarevaes.touragency.domain.usecases;

import ru.mirea.zubarevaes.touragency.data.repository.TensorFlowLite;
import ru.mirea.zubarevaes.touragency.domain.repository.IFunctionRecognition;

public class FunctionRecognition implements IFunctionRecognition {
    private TensorFlowLite tensorFlowLite;

    public FunctionRecognition(TensorFlowLite tensorFlowLite) {
        this.tensorFlowLite = tensorFlowLite;
    }

    @Override
    public String execute(String imagePath) {
        return tensorFlowLite.recognizeTextFromImage(imagePath);
    }
}