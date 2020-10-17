package com.dreamsoft.mdiwindows;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DsMDICanvas extends VBox {
    private final ScrollPane taskBar;
    private HBox tbWindows;
    private final AnchorPane paneMDIContainer;
    private DsMDICanvas mdiCanvas = this;
    private final int taskbarHeightWithoutScrool = 44;
    private final int taskbarHeightWithScrool = 54;
    public EventHandler<DsMDIEvent> mdiCloseHandler = (event) -> {
        DsMDIWindows win = (DsMDIWindows)event.getTarget();
        this.tbWindows.getChildren().remove(this.getItemFromToolBar(win.getId()));
        win = null;
    };
    public EventHandler<DsMDIEvent> mdiMinimizedHandler = (event) -> {
        DsMDIWindows win = (DsMDIWindows)event.getTarget();
        String id1 = win.getId();
        if (this.getItemFromToolBar(id1) == null) {
            try {
                DsMdiIcon icon = new DsMdiIcon(event.logo, this.mdiCanvas, win.getWindowsTitle());
                icon.setId(win.getId());
                icon.getBtnClose().disableProperty().bind(win.getBtnClose().disableProperty());
                this.tbWindows.getChildren().add(icon);
            } catch (Exception var5) {
                Logger.getLogger(DsMDICanvas.class.getName()).log(Level.SEVERE, (String)null, var5);
            }
        }

    };

    public DsMDICanvas() {
        this.setAlignment(Pos.TOP_LEFT);
        this.paneMDIContainer = new AnchorPane();
        this.paneMDIContainer.setId("MDIContainer");
        this.tbWindows = new HBox();
        this.tbWindows.setSpacing(3.0D);
        this.tbWindows.setMaxHeight(44.0D);
        this.tbWindows.setMinHeight(44.0D);
        this.tbWindows.setAlignment(Pos.CENTER_LEFT);
        setVgrow(this.paneMDIContainer, Priority.ALWAYS);
        this.taskBar = new ScrollPane(this.tbWindows);
        this.taskBar.setMaxHeight(44.0D);
        this.taskBar.setMinHeight(44.0D);
        this.taskBar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.taskBar.setVmax(0.0D);
        this.taskBar.setStyle(" -fx-border-color: #C4C4C4;  -fx-faint-focus-color: transparent;  -fx-focus-color: transparent; ");
        this.tbWindows.widthProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if ((Double)newValue <= this.taskBar.getWidth()) {
                    this.taskBar.setMaxHeight(44.0D);
                    this.taskBar.setPrefHeight(44.0D);
                    this.taskBar.setMinHeight(44.0D);
                } else {
                    this.taskBar.setMaxHeight(54.0D);
                    this.taskBar.setPrefHeight(54.0D);
                    this.taskBar.setMinHeight(54.0D);
                }

            });
        });
        this.getChildren().addAll(new Node[]{this.paneMDIContainer, this.taskBar});
        this.addEventHandler(DsMDIEvent.EVENT_CLOSED, this.mdiCloseHandler);
        this.addEventHandler(DsMDIEvent.EVENT_MINIMIZED, this.mdiMinimizedHandler);
    }

    public AnchorPane getPaneMDIContainer() {
        return this.paneMDIContainer;
    }

    public HBox getTbWindows() {
        return this.tbWindows;
    }

    public void removeMDIWindow(String mdiWindowID) {
        Node mdi = this.getItemFromMDIContainer(mdiWindowID);
        Node iconBar = this.getItemFromToolBar(mdiWindowID);
        if (mdi != null) {
            this.paneMDIContainer.getChildren().remove(mdi);
        }

        if (iconBar != null) {
            this.tbWindows.getChildren().remove(iconBar);
        }

    }

    public void addMDIWindow(DsMDIWindows mdiWindow) {
        if (this.getItemFromMDIContainer(mdiWindow.getId()) == null) {
            mdiWindow.setVisible(false);
            this.paneMDIContainer.getChildren().add(mdiWindow);
            Platform.runLater(() -> {
                this.centerMdiWindow(mdiWindow);
                mdiWindow.setVisible(true);
            });
            mdiWindow.toFront();
        } else {
            if (this.getItemFromToolBar(mdiWindow.getId()) != null) {
                this.tbWindows.getChildren().remove(this.getItemFromToolBar(mdiWindow.getId()));
            }

            for(int i = 0; i < this.paneMDIContainer.getChildren().size(); ++i) {
                Node node = (Node)this.paneMDIContainer.getChildren().get(i);
                if (node.getId().equals(mdiWindow.getId())) {
                    ((DsMDIWindows)node).toFront();
                    ((DsMDIWindows)node).setVisible(true);
                }
            }
        }

    }

    public DsMdiIcon getItemFromToolBar(String id) {
        Iterator var2 = this.tbWindows.getChildren().iterator();

        while(var2.hasNext()) {
            Node node = (Node)var2.next();
            if (node instanceof DsMdiIcon) {
                DsMdiIcon icon = (DsMdiIcon)node;
                String key = icon.getId();
                if (key.equalsIgnoreCase(id)) {
                    return icon;
                }
            }
        }

        return null;
    }

    public DsMDIWindows getItemFromMDIContainer(String id) {
        Iterator var2 = this.paneMDIContainer.getChildren().iterator();

        while(var2.hasNext()) {
            Node node = (Node)var2.next();
            if (node instanceof DsMDIWindows) {
                DsMDIWindows win = (DsMDIWindows)node;
                if (win.getId().equals(id)) {
                    return win;
                }
            }
        }

        return null;
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
}
