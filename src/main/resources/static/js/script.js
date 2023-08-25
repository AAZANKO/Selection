function pushRules(list){

    var li = document.createElement("li");

    var inputBtn = document.createElement("input");
    inputBtn.type = "text";
    inputBtn.value = "Remove";
    li.appendChild(inputBtn);
    document.getElementById("list").appendChild(li);

    var select = document.createElement('select');
    // Use the Option constructor: args text, value, defaultSelected, selected
    var option = new Option('', '', false, false);
    select.appendChild(option);

    option = document.createElement('option');
    option.value = 'A90_1';
    option.text = '90.1';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_2';
    option.text = '90.2';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_4';
    option.text = '90.4';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_5';
    option.text = '90.5';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_6';
    option.text = '90.6';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_7';
    option.text = '90.7';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_8';
    option.text = '90.8';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A90_10';
    option.text = '90.10';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A91_1';
    option.text = '91.1';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A91_2';
    option.text = '91.2';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A91_3';
    option.text = '91.3';
    select.appendChild(option);
    option = document.createElement('option');
    option.value = 'A91_4';
    option.text = '91.4';
    select.appendChild(option);

    select.className = "css-class-name";

    li.appendChild(select);
    document.getElementById("list").appendChild(li);

    var removeBtn = document.createElement("input");
    removeBtn.type = "button";
    removeBtn.value = "Remove";
    removeBtn.onclick = remove;
    li.appendChild(removeBtn);
    document.getElementById("list").appendChild(li);
}

function remove(e) {
    var el = e.target;
    el.parentNode.remove();
}