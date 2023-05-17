package com.zaga;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zaga.model.entity.SequenceCounters;
import com.zaga.repository.SequenceRepository;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class MongoStartUp {

    @Inject
    MongoClient mongoClient;

    @Inject
    SequenceRepository repo;

    void onStart(@Observes StartupEvent event) throws IOException, URISyntaxException {

        List<SequenceCounters> counters = repo.listAll();

        if (counters.isEmpty()) {

            MongoDatabase database = mongoClient.getDatabase("ProjectManagement");

            MongoCollection<Document> collection = database.getCollection("counter");

            // URL resource =
            // getClass().getClassLoader().getResource("CounterCollection.json");

            // Path path = Paths.get(resource.toURI());

            // String jsonAsString = new String(Files.readAllBytes(path));

            String json = "[{\"seqName\":\"Project\",\"seqNumber\":0},{\"seqName\":\"MeetingMinutes\",\"seqNumber\":0},{\"seqName\":\"DailyTimesheet\",\"seqNumber\":0},{\"seqName\":\"WeeklyTimesheet\",\"seqNumber\":0},{\"seqName\":\"ApprovedTimesheet\",\"seqNumber\":0}]";

            System.out.println(json);

            // collection.insertOne(Document.parse(jsonAsString));

            List<Document> documents = new ArrayList<>();
            JsonArray jsonArray = Json.createReader(new StringReader(json)).readArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.getJsonObject(i);
                Document doc = Document.parse(obj.toString());
                documents.add(doc);
            }

            collection.insertMany(documents);

        }
    }

}
