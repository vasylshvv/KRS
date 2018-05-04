$(document).ready(function(){

  $('.btn-filter').on('click', function () {
    $('.report-filter').toggle('slow');
  });

  $(".together").css('font-weight', 'bold');
  sumUnak();

  $("#rep").css('font-size','25px').css('text-decoration', 'underline');

  $('body').on('change', '#filter-okruha', filterItems)
    .on('change', '#filter-stanytsya', filterItems)
    .on('change', '#filter-status', filterItems);

  function filterItems() {
    $('.panel').show();
    $('.table.kurin thead, tr').show();
    $('.datakurin, .okruha, .stanytsya').show();

    var okruha = $('#filter-okruha').val(),
      stanytsya = $('#filter-stanytsya').val(),
      status = $('#filter-status').val();

    if ($(this).attr('id') === 'filter-okruha' && okruha !== '0') {
      $('.okruha, .stanytsya').hide();
      $('#filter-stanytsya').val('0');
      stanytsya = 0;
      $('.okruha[data-okruha=' + okruha + ']').show();
      $('.okruha[data-okruha=' + okruha + '] .stanytsya').show();
    } else if ($(this).attr('id') === 'filter-stanytsya') {
      $('.okruha, .stanytsya').hide();
      $('#filter-okruha').val('0');
      okruha = 0;
      $('.stanytsya[data-stanytsya=' + stanytsya + ']').show();
      $('.stanytsya[data-stanytsya=' + stanytsya + ']').closest('.okruha').show();
    } else {
      if (okruha != 0) {
        $('.okruha, .stanytsya').hide();
        $('.okruha[data-okruha=' + okruha + ']').show();
        $('.okruha[data-okruha=' + okruha + '] .stanytsya').show();
      } else if (stanytsya != 0) {
        $('.okruha, .stanytsya').hide();
        $('.stanytsya[data-stanytsya=' + stanytsya + ']').show();
        $('.stanytsya[data-stanytsya=' + stanytsya + ']').closest('.okruha').show();
      }
    }

    if (status !== '-1') {
      $('.datakurin').hide();
      $('.datakurin[data-status=' + status + ']').show();
      $('#report .panel-body tbody').each(function () {
        if (!$(this).find('tr:visible').length) {
          $(this).prev().hide();
        }
      });
      $('#report .panel').each(function () {
        if ($(this).find('thead:visible').length === 1) {
          $(this).hide();
        }
      });
    }

    sumUnak();
  }

  function sumUnak() {
    $('.kurin').each(function(){
      var myVar = $(this).find('.datakurin').text();

      $(this).find('tbody').each(function () {
        if (!$(this).find('.datakurin').length) {
          $(this).prev().remove();
          $(this).remove();
        }
      });
      $(this).fadeIn();

      if(myVar==""){
        $(this).find(".together").empty();
      } else {
        var prykh = 0;
        $(this).find('.datakurin:visible .prykh').each(function() { prykh +=  parseInt($(this).text());});
        $(this).find('.allprykh').text(prykh).css('text-align','center');
        $(this).find('.datakurin:visible .prykh').css('text-align','center');

        var uch = 0;
        $(this).find('.datakurin:visible .uch').each(function() {uch +=  parseInt($(this).text());});
        $(this).find('.alluch').text(uch).css('text-align','center');
        $(this).find('.datakurin:visible .uch').css('text-align','center');

        var rozv = 0;
        $(this).find('.datakurin:visible .rozv').each(function() {rozv +=  parseInt($(this).text());});
        $(this).find('.allrozv').text(rozv).css('text-align','center');
        $(this).find('.datakurin:visible .rozv').css('text-align','center');

        var skbvirl = 0;
        $(this).find('.datakurin:visible .skbvirl').each(function() {skbvirl +=  parseInt($(this).text());});
        $(this).find('.allskbvirl').text(skbvirl).css('text-align','center');
        $(this).find('.datakurin:visible .skbvirl').css('text-align','center');

        var htskbvirl = 0;
        $(this).find('.datakurin:visible .htskbvirl').each(function() {htskbvirl +=  parseInt($(this).text());});
        $(this).find('.allhtskbvirl').text(htskbvirl).css('text-align','center');
        $(this).find('.datakurin:visible .htskbvirl').css('text-align','center');

        var count = 0;
        $(this).find('.datakurin:visible .count').each(function() {count +=  parseInt($(this).text());});
        $(this).find('.allcount').text(count).css('text-align','center');
        $('.count').css('font-weight', 'bold').css('text-align','center');
        $('.allcount').css('font-weight', 'bold').css('text-align','center').css('font-size','20px');
      }
    });

    $('.kurin').each(function(){
      var allprykh = 0;
      $('.allprykh').each(function() {allprykh +=  parseInt($(this).text());});
      $('.allsumprykh').text(allprykh).css('font-size','20px');

      var alluch = 0;
      $('.alluch').each(function() {alluch +=  parseInt($(this).text());});
      $('.allsumuch').text(alluch).css('font-size','20px');

      var allrozv = 0;
      $('.allrozv').each(function() {allrozv +=  parseInt($(this).text());});
      $('.allsumrozv').text(allrozv).css('font-size','20px');

      var allskbvirl = 0;
      $('.allskbvirl').each(function() {allskbvirl +=  parseInt($(this).text());});
      $('.allsumskbvirl').text(allskbvirl).css('font-size','20px');

      var allhtskbvirl = 0;
      $('.allhtskbvirl').each(function() {allhtskbvirl +=  parseInt($(this).text());});
      $('.allsumhtskbvirl').text(allhtskbvirl).css('font-size','20px');

      var allcount = 0;
      $('.allcount').each(function() {allcount +=  parseInt($(this).text());});
      $('.allsumcount').text(allcount).css('font-size','30px');
    });
  }
});