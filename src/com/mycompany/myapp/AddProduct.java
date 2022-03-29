/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.exercice;
import com.mycompany.myapp.entities.product;
import com.mycompany.myapp.entities.productCategory;
import com.mycompany.myapp.entities.typeExercice;
import com.mycompany.myapp.services.exerciceService;
import com.mycompany.myapp.services.productService;

/**
 *
 * @author louay
 */
public class AddProduct extends SideMenuBaseForm {

    public AddProduct(Resources res, productCategory publication) {
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
                                new Label("Add product", "Title")
                        )
                )
        );

        tb.setTitleComponent(titleCmp);

        TextField nom = new TextField("", "Nom...");
        nom.getStyle().setBgColor(0x000000);
        nom.getStyle().setFgColor(0x000000);
        nom.getStyle().setBorder(Border.createRoundBorder(50, 50));
        nom.getStyle().setElevation(1);
        nom.getStyle().setPadding(3, 3, 0, 0);
        nom.getStyle().setUnderline(false);

        TextField price = new TextField("", "price...");
        price.getStyle().setBgColor(0x000000);
        price.getStyle().setFgColor(0x000000);
        price.getStyle().setBorder(Border.createRoundBorder(50, 50));
        price.getStyle().setElevation(1);
        price.getStyle().setPadding(3, 3, 0, 0);
        price.getStyle().setUnderline(false);

        TextField description = new TextField("", "Description...");
        description.getStyle().setBgColor(0x000000);
        description.getStyle().setFgColor(0x000000);
        description.getStyle().setBorder(Border.createRoundBorder(50, 50));
        description.getStyle().setElevation(1);
        description.getStyle().setPadding(3, 3, 0, 0);
        description.getStyle().setUnderline(false);

        TextField image = new TextField("", "Image...");
        image.getStyle().setBgColor(0x000000);
        image.getStyle().setFgColor(0x000000);
        image.getStyle().setBorder(Border.createRoundBorder(50, 50));
        image.getStyle().setElevation(1);
        image.getStyle().setPadding(3, 3, 0, 0);
        image.getStyle().setUnderline(false);

        TextField quantity = new TextField("", "Quantity...");
        quantity.getStyle().setBgColor(0x000000);
        quantity.getStyle().setFgColor(0x000000);
        quantity.getStyle().setBorder(Border.createRoundBorder(50, 50));
        quantity.getStyle().setElevation(1);
        quantity.getStyle().setPadding(3, 3, 0, 0);
        quantity.getStyle().setUnderline(false);

        Button addPub = new Button("Add");
        addPub.getStyle().setBgColor(0xffffff);
        addPub.getStyle().setFgColor(0x0583D2);
        addPub.getStyle().setBgTransparency(255);
        addPub.getStyle().setBorder(Border.createRoundBorder(30, 30));
        addPub.getStyle().setMargin(13, 13, 80, 80);
        addPub.getStyle().setPadding(3, 3, 0, 0);

        Container pub = BoxLayout.encloseY(
                BorderLayout.center(
                        BoxLayout.encloseY(
                                nom, description, price, quantity, image, addPub
                        )
                )
        );
        pub.getStyle().setPadding(70, 70, 40, 40);

        add(pub);

        addPub.addActionListener(l -> {
            if (description.getText().equals("") || nom.getText().equals("") || quantity.getText().equals("") || quantity.getText().equals("") || price.getText().equals("")) {
                Dialog.show("Error", "Veuillez vérifier les données", "OK", null);
            } else {
                InfiniteProgress ip = new InfiniteProgress();;
                final Dialog iDialog = ip.showInfiniteBlocking();
                String categoryId = Integer.toString(publication.getId());

                product com = new product(nom.getText(), price.getText(), description.getText(), quantity.getText(), image.getText(), categoryId);
                productService.getInstance().addProduct(com, publication);
                iDialog.dispose();
//showprodcuts
                new ShowProduct(res).show();
                /*new (res).show();
                new ShowPub(res).show();*/
                refreshTheme();

            }
        });
        setupSideMenu(res);
    }

    @Override

    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}