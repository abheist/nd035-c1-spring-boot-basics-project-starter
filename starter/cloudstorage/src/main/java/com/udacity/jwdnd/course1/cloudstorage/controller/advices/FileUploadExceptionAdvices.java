package com.udacity.jwdnd.course1.cloudstorage.controller.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileUploadExceptionAdvices {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(RedirectAttributes redirectAttributes, MaxUploadSizeExceededException ex) {
        redirectAttributes.addFlashAttribute("resultError", String.format(ex.getMessage()));

        return "redirect:/home/result";
    }

}
