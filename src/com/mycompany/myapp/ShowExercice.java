/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.exercice;
import com.mycompany.myapp.services.exerciceService;
import java.util.ArrayList;

/**
 *
 * @author louay
 */
public class ShowExercice extends SideMenuBaseForm {

    public ShowExercice(Resources res) {
        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Exercice", "Title")
                        )
                )
        );

        tb.setTitleComponent(titleCmp);

        add(new Label("Exercice", ""));

        ArrayList<exercice> Publications = exerciceService.getInstance().showProducts();

        for (exercice pubs : Publications) {

            Button editPost = new Button("Edit");
            editPost.getStyle().setBgColor(0xffffff);
            editPost.getStyle().setFgColor(0xFFA500);
            editPost.getStyle().setBgTransparency(255);
            editPost.getStyle().setBorder(Border.createRoundBorder(30, 30));
            editPost.getStyle().setPadding(1, 1, 1, 1);
            editPost.getStyle().setMargin(2, 2, 2, 2);
            editPost.addActionListener((l) -> {
                //new EditPub(res, pubs).show();
                new EditExercice(res, pubs).show();
            });

            Button deletePost = new Button("Delete");
            deletePost.getStyle().setBgColor(0xffffff);
            deletePost.getStyle().setFgColor(0xFF0000);
            deletePost.getStyle().setBgTransparency(255);
            deletePost.getStyle().setBorder(Border.createRoundBorder(30, 30));
            deletePost.getStyle().setPadding(1, 1, 1, 1);
            deletePost.getStyle().setMargin(2, 2, 2, 2);
            deletePost.addActionListener((l) -> {
                exerciceService.getInstance().deleteProduct(pubs.getId());
                if (exerciceService.getInstance().deleteProduct(pubs.getId())) {
                    Dialog.show("Success", "Post deleted", "OK", null);
                    new ShowExercice(res).show();
                    refreshTheme();
                }
            });
//String username = user.getUsername();
            Label nom = new Label("Nom: " + pubs.getNom());
            nom.getAllStyles().setFgColor(0xffffff);

            Label video = new Label("Video: " + pubs.getVideo());
            video.getAllStyles().setFgColor(0xffffff);

            Label description = new Label("Description: " + pubs.getDescription());
            description.getAllStyles().setFgColor(0xffffff);

            Label objectif = new Label("Objectif: " + pubs.getObjectif());
            objectif.getAllStyles().setFgColor(0xffffff);

            Container post = BoxLayout.encloseY(
                    GridLayout.encloseIn(2, objectif, description), video);
            Container first = GridLayout.encloseIn(1, editPost);
            Container second = GridLayout.encloseIn(1, deletePost);
            Container pub = BoxLayout.encloseY(
                    BorderLayout.centerAbsolute(
                            BoxLayout.encloseY(
                                    nom
                            )
                    ),//.add(BorderLayout.WEST, pubImage),
                    BoxLayout.encloseY(post, first, second)
            );

            pub.getStyle().setFgColor(0xffffff);
            pub.getStyle().setBgColor(0xfdb819);
            pub.getStyle().setBgTransparency(255);
            pub.getStyle().setPadding(7, 7, 7, 7);
            pub.getStyle().setMargin(20, 20, 30, 30);
            pub.getStyle().setBorder(Border.createRoundBorder(50, 50));

            add(pub);
        }
        setupSideMenu(res);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }

}
