<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            </div>
        </main>
        <!-- /MAIN SECTION -->
    </div>

    <!-- FOOTER -->
    <footer class="footer">
        <div class="container">
            <div class="col-sm-3">
                <a href="https://youtu.be/l7KTcDNfVpY" target="_blank" class="href-faq">Відео-інструкція</a>
            </div>
            <div class="col-sm-6">
                <p class="text-center">© Пласт НСОУ. КБ УПЮ. 2017. | <a href="mailto:vasylshvv@gmail.com">Контакти адміністраторів</a></p>
            </div>
        </div>
    </footer>
    <!-- /FOOTER -->
</div>

<div id="dialog_report_error" class="modal fade" role="dialog" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">Форма зворотнього зв'язку</h4>
            </div>
            <div class="modal-body">
                <form id="form-report">
                    <div class="form-group">
                        <label for="report_fullname">Ім'я та прізвище</label>
                        <input type="text" name="sfullname" id="report_fullname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="report_email">Email</label>
                        <input type="text" name="email" id="report_email" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="report_text">Текст повідомлення</label>
                        <textarea name="textrequest" id="report_text" class="form-control"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="report_text">Файл</label>
                        <div class="input-group">
                            <label class="btn btn-default btn-file">
                                Додати файл <input type="file" id="report_file" accept="image/*, application/pdf, application/rtf, application/x-rtf, application/msword" style="display: none;">
                            </label>
                            <span class="name-file"></span>
                        </div>
                    </div>
                </form>
                <div class="alert alert-danger notcorrect" style="display: none;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-cancel" data-dismiss="modal">Відмінити</button>
                <button type="button" class="btn btn-black btn-save">Відправити</button>
            </div>
        </div>
    </div>
</div>
<div id="dialog_report_text" class="modal fade" role="dialog" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">Інформація</h4>
            </div>
            <div class="modal-body">
            </div>
        </div>
    </div>
</div>