package lermitage.intellij.extra.icons.cfg;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.IconUtil;
import com.intellij.util.ImageLoader;
import com.intellij.util.ui.JBImageIcon;
import lermitage.intellij.extra.icons.Model;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ModelEditor extends DialogWrapper {

    private JPanel pane;
    private com.intellij.ui.components.JBTextField modelIDField;
    private com.intellij.ui.components.JBTextField descriptionField;
    private JComboBox<String> typeComboBox;
    private JLabel iconLabel;
    private JButton chooseIconButton;

    private List<String> extensions = Arrays.asList("svg", "png");

    protected ModelEditor() {
        super(true);
        init();
        setTitle("Edit Model");
        chooseIconButton.addActionListener(e -> {
            VirtualFile[] virtualFiles = FileChooser.chooseFiles(
                new FileChooserDescriptor(true, false, false, false, false, false)
                    .withFileFilter(file -> extensions.contains(file.getExtension())),
                null,
                null);
            if (virtualFiles.length > 0) {
                try {
                    Image image = ImageLoader.loadCustomIcon(new File(virtualFiles[0].getPath()));
                    JBImageIcon imageIcon = IconUtil.createImageIcon(image);
                    iconLabel.setIcon(imageIcon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return pane;
    }

    public void setData(Model model) {
        modelIDField.setText(model.getId());
        descriptionField.setText(model.getDescription());
        typeComboBox.setSelectedItem(model.getModelType().name());
        iconLabel.setIcon(IconLoader.getIcon(model.getIcon()));
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if (descriptionField.getText().isEmpty()) {
            return new ValidationInfo("Description cannot be empty!", descriptionField);
        }
        return super.doValidate();
    }
}
