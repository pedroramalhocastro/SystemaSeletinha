package com.system.seletinha.endpoint.controller;


import com.google.gson.Gson;
import com.system.seletinha.endpoint.services.StudentsService;
import com.system.seletinha.models.ServiceModel;
import com.system.seletinha.models.StudentModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentsController {
    private final StudentsService studentsService;


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public @ResponseBody ServiceModel createStudent(final HttpServletRequest request) {
        String name = request.getParameter("name");
        int series = Integer.parseInt(request.getParameter("series"));
        int registry = Integer.parseInt(request.getParameter("registry"));
        int team = Integer.parseInt(request.getParameter("team"));

        String resp = studentsService.create(new StudentModel(name,series, registry, team));
        return new ServiceModel(200, resp);
    }


    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public @ResponseBody String searchStudent(final HttpServletRequest request) {
        final String parameter = request.getParameter("registry");
        final Gson json = new Gson();

        if (parameter != null) {
            return json.toJson(studentsService.searchId(parameter));
        }

        return json.toJson(studentsService.searchAll());
    }


    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public @ResponseBody ServiceModel updateStudent(final HttpServletRequest request) {
        String name = request.getParameter("name");
        int series = Integer.parseInt(request.getParameter("series"));
        int registry = Integer.parseInt(request.getParameter("registry"));
        int team = Integer.parseInt(request.getParameter("team"));

        String resp = studentsService.update(new StudentModel(name, series, registry, team));

        return new ServiceModel(200, resp);

    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public @ResponseBody ServiceModel deleteStudent(final HttpServletRequest request) {
        String registry = request.getParameter("registry");
        String resp = studentsService.delete(registry);
        return new ServiceModel(200, resp);
    }

}
