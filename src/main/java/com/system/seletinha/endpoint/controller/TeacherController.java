package com.system.seletinha.endpoint.controller;


import com.google.gson.Gson;
import com.system.seletinha.endpoint.services.TeacherService;
import com.system.seletinha.models.ServiceModel;
import com.system.seletinha.models.TeachersModel;
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
public class TeacherController {

    private final TeacherService teacherService;


    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public @ResponseBody ServiceModel createStudent(final HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int registry = Integer.parseInt(request.getParameter("registry"));
        int team = Integer.parseInt(request.getParameter("team"));

        String resp = teacherService.create(new TeachersModel(name, email, password, registry, team));
        return new ServiceModel(200, resp);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public @ResponseBody String searchStudent(final HttpServletRequest request) {
        final String parameter = request.getParameter("registry");
        final Gson json = new Gson();

        if (parameter != null) {
            return json.toJson(teacherService.searchId(parameter));
        }

        return json.toJson(teacherService.searchAll());
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.PUT)
    public @ResponseBody ServiceModel updateStudent(final HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int registry = Integer.parseInt(request.getParameter("registry"));
        int team = Integer.parseInt(request.getParameter("team"));

        String resp = teacherService.update(new TeachersModel(name, email, password, registry, team));

        return new ServiceModel(200, resp);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.DELETE)
    public @ResponseBody ServiceModel deleteStudent(final HttpServletRequest request) {
        String registry = request.getParameter("registry");
        String resp = teacherService.delete(registry);
        return new ServiceModel(200, resp);
    }

}
