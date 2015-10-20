package de.zortax.oneshot.plugin;
//  Created by Leonard on 16.10.2015.

import de.zortax.oneshot.OneShot;
import de.zortax.oneshot.shop.ShopItem;
import org.bukkit.Bukkit;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

public class PluginManager {

    private OneShot os;
    private ArrayList<ShopItem> plugins;

    public PluginManager (OneShot os) {
        this.os = os;
    }

    public void loadPlugins () throws MalformedURLException {
        plugins = new ArrayList<>();

        File loc = new File(os.getDataFolder().toString() + "\\" + "plugins");
        if(!loc.exists()) loc.mkdir();

        File[] flist = loc.listFiles(file -> {return file.getPath().toLowerCase().endsWith(".jar");});
        URL[] urls = new URL[flist.length];
        for (int i = 0; i < flist.length; i++)
            urls[i] = flist[i].toURI().toURL();
        URLClassLoader ucl = new URLClassLoader(urls);

        ServiceLoader<ShopItem> sl = ServiceLoader.load(ShopItem.class, ucl);
        Iterator<ShopItem> apit = sl.iterator();
        while (apit.hasNext()) {
            ShopItem plugin = apit.next();
            Bukkit.getLogger().info("Das OneShot-Plugin " + plugin.getName() + " wurde geladen...");
            plugins.add(plugin);

        }

    }

}
