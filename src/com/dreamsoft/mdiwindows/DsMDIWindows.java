package com.dreamsoft.mdiwindows;

import com.dreamsoft.utils.DsUtils;
import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;

public class DsMDIWindows extends BorderPane {
    private double mousex = 0.0D;
    private double mousey = 0.0D;
    private double x = 0.0D;
    private double y = 0.0D;
    private JFXButton btnClose;
    private JFXButton btnMinimize;
    private JFXButton btnMaximize;
    private BorderPane borderPane = this;
    private boolean isMaximized = false;
    private boolean isClosed = false;
    private ResizeMode resizeMode;
    private boolean RESIZE_TOP;
    private boolean RESIZE_LEFT;
    private boolean RESIZE_BOTTOM;
    private boolean RESIZE_RIGHT;
    private double previousWidthToResize;
    private double previousHeightToResize;
    private final ImageView logo;
    private Label lblTitle;
    private boolean disableResize = false;
    private double lastY;
    private double lastX;
    private final String windowsTitle;
    private AnchorPane paneMDIContainer;

    public DsMDIWindows(String mdiID, ImageView logoImage, String title, Node content) throws Exception {
        this(mdiID, logoImage, title, content,false);
    }

    public DsMDIWindows(String mdiID, ImageView logoImage, String title, Node content, boolean disableResize) throws Exception {
        this(mdiID, logoImage, title, content,disableResize,false);
    }

    public DsMDIWindows(String mdiID, ImageView logo, String windowsTitle, Node content, boolean disableResize, boolean maximize) throws Exception {
        this.getStylesheets().add(getClass().getResource(
                "/com/dreamsoft/mdiwindows/dsMDIWindowsStyles.css").toExternalForm());
        this.disableResize = disableResize;
        this.logo=logo;
        this.windowsTitle=windowsTitle;
        this.init(mdiID, windowsTitle, content);
        if (maximize) {
            Platform.runLater(() -> {
                this.centerMdiWindow(this);
                this.maximize();
            });
        }

    }

    private void init(String mdiID, String title, Node content) throws Exception {
        this.setId(mdiID);
        Platform.runLater(() -> this.paneMDIContainer = (AnchorPane)this.getParent());
        this.moveListener();
        this.bringToFrontListener();
        this.setPrefSize(200.0D, 200.0D);
        this.setTop(this.makeTitlePane(title));
        AnchorPane mdiContent = this.makeContentPane(content);
        this.setCenter(mdiContent);
    }

    public void setMdiTitle(String title) {
        this.lblTitle.setText(title);
    }

    private AnchorPane makeTitlePane(String title) throws Exception {
        AnchorPane paneTitle = new AnchorPane();
        paneTitle.setPrefHeight(32.0D);
        paneTitle.getStyleClass().add("titlepane");
        paneTitle.getChildren().add(this.makeTitle(title));
        paneTitle.setPadding(new Insets(0.0D, 11.0D, 0.0D, 0.0D));
        this.btnClose =new JFXButton();
        btnClose.setGraphic(getImageFromResource("closeformwhite.png"));
        btnClose.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.btnClose.setOnMouseClicked((t) -> {
            this.closeWindow();
        });
        this.btnMinimize =new JFXButton();
        btnMinimize.setGraphic(getImageFromResource("niminizeformwhite.png"));
        btnMinimize.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.btnMinimize.setOnMouseClicked((t) -> {
            this.borderPane.setVisible(false);
            this.borderPane.fireEvent(new DsMDIEvent(this.logo, DsMDIEvent.EVENT_MINIMIZED));
        });
        this.btnMaximize = new JFXButton();
        btnMaximize.setGraphic(getImageFromResource("maximizeformwhite.png"));
        btnMaximize.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.btnMaximize.setOnMouseClicked((t) -> {
            this.maximize();
        });
        if (!this.disableResize) {
            paneTitle.getChildren().add(this.makeControls(this.btnMinimize, this.btnMaximize, this.btnClose));
            paneTitle.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2) {
                    this.maximize();
                }

            });
        } else {
            paneTitle.getChildren().add(this.makeControls(this.btnMinimize, this.btnClose));
        }

        return paneTitle;
    }

    private void maximize() {
        Pane parent = (Pane)this.getParent();
        if (!this.isMaximized) {
            this.lastX = this.getLayoutX();
            this.lastY = this.getLayoutY();
            this.previousHeightToResize = this.getHeight();
            this.previousWidthToResize = this.getWidth();
            this.isMaximized = true;

            try {
                this.btnMaximize.setGraphic(this.getImageFromResource("restoreformwhite.png"));
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }

            this.addListenerToResizeMaximizedWindows();
        } else {
            this.setPrefSize(this.previousWidthToResize, this.previousHeightToResize);
            this.isMaximized = false;

            try {
                this.btnMaximize.setGraphic(this.getImageFromResource("maximizeformwhite.png"));
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }

            this.removeListenerToResizeMaximizedWindows();
        }

    }

    private AnchorPane makeContentPane(Node content) {
        AnchorPane paneContent = new AnchorPane();
        paneContent.setStyle("-fx-background-color: #F9F9F9; -fx-border-color: #F2F2F2; -fx-border-width: 1.5px;");
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
        AnchorPane.setTopAnchor(content, 0.0);
        paneContent.getChildren().add(content);
        return paneContent;
    }

    private HBox makeTitle(String title) throws Exception {
        HBox hbLeft = new HBox();
        hbLeft.setSpacing(10.0D);
        ImageView imvLogo = this.logo != null ? this.logo : new ImageView();
        imvLogo.setPreserveRatio(true);
        imvLogo.setFitWidth(24.0);
        imvLogo.setFitHeight(24.0);
        this.lblTitle = new Label(title);
        this.lblTitle.getStyleClass().add("tilelabe");
        hbLeft.getChildren().add(imvLogo);
        hbLeft.getChildren().add(this.lblTitle);
        hbLeft.setAlignment(Pos.CENTER_LEFT);
        AnchorPane.setLeftAnchor(hbLeft, 10.0D);
        AnchorPane.setBottomAnchor(hbLeft, 0.0D);
        AnchorPane.setRightAnchor(hbLeft, 20.0D);
        AnchorPane.setTopAnchor(hbLeft, 0.0D);
        return hbLeft;
    }

    private HBox makeControls(Node... nodes) {
        HBox hbRight = new HBox();
        hbRight.getChildren().addAll(nodes);
        hbRight.setAlignment(Pos.CENTER_RIGHT);
        AnchorPane.setBottomAnchor(hbRight, 0.0D);
        AnchorPane.setRightAnchor(hbRight, 0.0D);
        AnchorPane.setTopAnchor(hbRight, 0.0D);
        return hbRight;
    }

    private void moveListener() {
        this.setOnMouseDragged((dragEvent) -> {
            if (!this.isMaximized) {
                this.x += dragEvent.getSceneX() - this.mousex;
                this.y += dragEvent.getSceneY() - this.mousey;
                this.mousex = dragEvent.getSceneX();
                this.mousey = dragEvent.getSceneY();
                if (this.resizeMode == ResizeMode.NONE) {
                    if (this.borderPane.getWidth() < this.borderPane.getParent().getLayoutBounds().getWidth()) {
                        this.borderPane.setLayoutX(this.x);
                    }

                    if (this.borderPane.getHeight() < this.borderPane.getParent().getLayoutBounds().getHeight()) {
                        this.borderPane.setLayoutY(this.y);
                    }

                    if (this.borderPane.getLayoutX() <= this.borderPane.getParent().getLayoutX()) {
                        this.borderPane.setLayoutX(this.borderPane.getParent().getLayoutX());
                    } else if (this.borderPane.getLayoutX() + this.borderPane.getWidth() >= this.borderPane.getParent().getLayoutBounds().getWidth()) {
                        this.borderPane.setLayoutX(this.borderPane.getParent().getLayoutBounds().getWidth() - this.borderPane.getWidth());
                    }

                    if (this.borderPane.getLayoutY() <= this.borderPane.getParent().getLayoutX()) {
                        this.borderPane.setLayoutY(this.borderPane.getParent().getLayoutY());
                    } else if (this.borderPane.getLayoutY() + this.borderPane.getHeight() >= this.borderPane.getParent().getLayoutBounds().getHeight()) {
                        this.borderPane.setLayoutY(this.borderPane.getParent().getLayoutBounds().getHeight() - this.borderPane.getHeight());
                    }
                } else if (!this.disableResize) {
                    if (this.RESIZE_RIGHT && !this.RESIZE_TOP && !this.RESIZE_BOTTOM) {
                        if (dragEvent.getX() <= this.getParent().getLayoutBounds().getWidth() - this.getLayoutX()) {
                            this.setPrefWidth(dragEvent.getX());
                        } else {
                            this.setPrefWidth(this.getParent().getLayoutBounds().getWidth() - this.getLayoutX());
                        }
                    } else if (!this.RESIZE_RIGHT && !this.RESIZE_TOP && this.RESIZE_BOTTOM) {
                        if (dragEvent.getY() <= this.getParent().getLayoutBounds().getHeight() - this.getLayoutY()) {
                            this.setPrefHeight(dragEvent.getY());
                        } else {
                            this.setPrefHeight(this.getParent().getLayoutBounds().getHeight() - this.getLayoutY());
                        }
                    } else if (this.RESIZE_RIGHT && !this.RESIZE_TOP && this.RESIZE_BOTTOM) {
                        if (dragEvent.getX() <= this.getParent().getLayoutBounds().getWidth() - this.getLayoutX() && dragEvent.getY() <= this.getParent().getLayoutBounds().getHeight() - this.getLayoutY()) {
                            this.setPrefWidth(dragEvent.getX());
                            this.setPrefHeight(dragEvent.getY());
                        } else {
                            if (dragEvent.getX() <= this.getParent().getLayoutBounds().getWidth() - this.getLayoutX()) {
                                this.setPrefWidth(dragEvent.getX());
                            } else {
                                this.setPrefWidth(this.getParent().getLayoutBounds().getWidth() - this.getLayoutX());
                            }

                            if (dragEvent.getY() <= this.getParent().getLayoutBounds().getHeight() - this.getLayoutY()) {
                                this.setPrefHeight(dragEvent.getY());
                            } else {
                                this.setPrefHeight(this.getParent().getLayoutBounds().getHeight() - this.getLayoutY());
                            }
                        }
                    } else if (this.RESIZE_LEFT && !this.RESIZE_TOP && !this.RESIZE_BOTTOM) {
                    }
                }
            }

        });
        this.setOnMousePressed((event) -> {
            this.borderPane.toFront();
            this.mousex = event.getSceneX();
            this.mousey = event.getSceneY();
            this.x = this.borderPane.getLayoutX();
            this.y = this.borderPane.getLayoutY();
        });
        this.onMouseMovedProperty().set((t) -> {
            double scaleX = this.localToSceneTransformProperty().getValue().getMxx();
            double scaleY = this.localToSceneTransformProperty().getValue().getMyy();
            double border1 = 5.0D;
            double diffMinX = Math.abs(this.getBoundsInLocal().getMinX() - t.getX());
            double diffMinY = Math.abs(this.getBoundsInLocal().getMinY() - t.getY());
            double diffMaxX = Math.abs(this.getBoundsInLocal().getMaxX() - t.getX());
            double diffMaxY = Math.abs(this.getBoundsInLocal().getMaxY() - t.getY());
            boolean left1 = diffMinX * scaleX < 5.0D;
            boolean top1 = diffMinY * scaleY < 5.0D;
            boolean right1 = diffMaxX * scaleX < 5.0D;
            boolean bottom1 = diffMaxY * scaleY < 5.0D;
            this.RESIZE_TOP = false;
            this.RESIZE_LEFT = false;
            this.RESIZE_BOTTOM = false;
            this.RESIZE_RIGHT = false;
            if ((!left1 || top1 || bottom1) && (!left1 || !top1 || bottom1) && (!left1 || top1 || !bottom1)) {
                if (right1 && !top1 && !bottom1) {
                    if (!this.disableResize) {
                        this.setCursor(Cursor.E_RESIZE);
                    }

                    this.resizeMode = ResizeMode.RIGHT;
                    this.RESIZE_RIGHT = true;
                } else if (!right1 || !top1 || bottom1) {
                    if (right1 && !top1 && bottom1) {
                        if (!this.disableResize) {
                            this.setCursor(Cursor.SE_RESIZE);
                        }

                        this.resizeMode = ResizeMode.BOTTOM_RIGHT;
                        this.RESIZE_RIGHT = true;
                        this.RESIZE_BOTTOM = true;
                    } else if (!top1 || left1 || right1) {
                        if (bottom1 && !left1 && !right1) {
                            if (!this.disableResize) {
                                this.setCursor(Cursor.S_RESIZE);
                            }

                            this.resizeMode = ResizeMode.BOTTOM;
                            this.RESIZE_BOTTOM = true;
                        } else {
                            this.setCursor(Cursor.DEFAULT);
                            this.resizeMode = ResizeMode.NONE;
                        }
                    }
                }
            }

        });
    }

    public boolean closeConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        alert.setContentText("Desea cerrar esta ventana?");
        alert.initStyle(StageStyle.UTILITY);
        Optional<ButtonType> result=alert.showAndWait();
        return result.get()==ButtonType.OK;
    }

    public void closeWindow() {
        if(!closeConfirmation()){
            return;
        }
        ScaleTransition st = new ScaleTransition(Duration.millis(100.0D), this.borderPane);
        st.setToX(0.0D);
        st.setToY(0.0D);
        st.setByX(1.0D);
        st.setByY(1.0D);
        st.setCycleCount(1);
        st.play();
        this.borderPane.fireEvent(new DsMDIEvent(null, DsMDIEvent.EVENT_CLOSED));
        st.setOnFinished((t) -> {
            for(int i = 0; i < this.paneMDIContainer.getChildren().size(); ++i) {
                DsMDIWindows window = (DsMDIWindows)this.paneMDIContainer.getChildren().get(i);
                if (window.getId().equals(this.borderPane.getId())) {
                    this.paneMDIContainer.getChildren().remove(i);
                }
            }

            this.isClosed = true;
        });
    }

    private void bringToFrontListener() {
        this.setOnMouseClicked((t) -> {
            this.borderPane.toFront();
        });
    }

    private ImageView getImageFromResource(String imageName) throws Exception {
        return new ImageView(DsUtils.getInstance().getImageResources(imageName));
    }

    private void removeListenerToResizeMaximizedWindows() {
        AnchorPane.clearConstraints(this.borderPane);
        this.setLayoutX((double)((int)this.lastX));
        this.setLayoutY((double)((int)this.lastY));
    }

    private void addListenerToResizeMaximizedWindows() {
        AnchorPane.setBottomAnchor(this.borderPane, 0.0D);
        AnchorPane.setTopAnchor(this.borderPane, 0.0D);
        AnchorPane.setLeftAnchor(this.borderPane, 0.0D);
        AnchorPane.setRightAnchor(this.borderPane, 0.0D);
    }

    private ChangeListener<Number> setBorderPanePrefSizeListener() {
        return (observableValue, oldSceneWidth, newSceneWidth) -> {
            if (this.isMaximized) {
            }

        };
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public void isClosed(boolean value) {
        this.isClosed = value;
    }

    public JFXButton getBtnClose() {
        return this.btnClose;
    }


    public String getWindowsTitle() {
        return this.windowsTitle;
    }

    public Button getBtnMinimize() {
        return this.btnMinimize;
    }


    public void centerMdiWindow(DsMDIWindows mdiWindow) {
        try {
            double w = this.paneMDIContainer.getWidth();
            double h = this.paneMDIContainer.getHeight();
            double windowsHeight = ((AnchorPane)((AnchorPane)mdiWindow.getCenter()).getChildren().get(0)).getHeight() + ((AnchorPane)mdiWindow.getTop()).getHeight();
            double windowsWidth = ((AnchorPane)((AnchorPane)mdiWindow.getCenter()).getChildren().get(0)).getWidth();
            mdiWindow.setLayoutX((double)((int)(w / 2.0D) - (int)(windowsWidth / 2.0D)));
            mdiWindow.setLayoutY((double)((int)(h / 2.0D) - (int)(windowsHeight / 2.0D)));
            mdiWindow.setCache(false);
        } catch (Exception var10) {
        }

    }

    enum ResizeMode {
        NONE,
        TOP,
        LEFT,
        BOTTOM,
        RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT;
    }
}
