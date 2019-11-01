package com.example.main;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceExample {

	public static void main(String[] args) {
		try {
			WatchService service = FileSystems.getDefault().newWatchService();

			Path path = Paths.get("/Users/usuario/Desktop/watch_example");

			path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			
			WatchKey key = null;
			while ((key = service.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println("Tipo evento: "+ event.kind() + " Archivo: "+ event.context());
				}
				key.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
