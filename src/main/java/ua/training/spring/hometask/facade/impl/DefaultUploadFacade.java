package ua.training.spring.hometask.facade.impl;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.facade.UploadFacade;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.UserService;

import java.io.IOException;
import java.util.List;

@Component
public class DefaultUploadFacade implements UploadFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public void saveDataFromJsonFile(MultipartFile jsonFile) throws IOException {
        ObjectMapper mapper = buildMapperWithTimeModule();
        TreeNode node = getTreeNodeFromFile(jsonFile, mapper);
        eventService.saveAll(getEventsFromTreeNode(mapper, node));
        userService.saveAll(getUsersFromTreeNode(mapper, node));
    }

    private List<User> getUsersFromTreeNode(ObjectMapper mapper, TreeNode node) throws IOException {
        TreeNode usersNode = node.get("users");
        User[] users = mapper.treeToValue(usersNode, User[].class);

        return Lists.newArrayList(users);
    }

    private List<Event> getEventsFromTreeNode(ObjectMapper mapper, TreeNode node) throws IOException {
        TreeNode eventsNode = node.get("events");
        Event[] events = mapper.treeToValue(eventsNode, Event[].class);

        return Lists.newArrayList(events);
    }

    private ObjectMapper buildMapperWithTimeModule() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }

    private TreeNode getTreeNodeFromFile(MultipartFile jsonFile, ObjectMapper mapper) throws IOException {
        return mapper.readTree(jsonFile.getInputStream());
    }
}
