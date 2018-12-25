/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Select DOM Items
const menuBtn = document.querySelector('.menu-btn');
const menu = document.querySelector('.menu');
const menuNav = document.querySelector('.menu-nav');
//const menuBranding = document.querySelector('.menu-branding');
const navItems = document.querySelectorAll('.nav-item');

// Set Initial State Of Menu
let showMenu = false;

menuBtn.addEventListener('click', toggleMenu);

function toggleMenu() {
  if (!showMenu) {
    menuBtn.classList.add('close');
    menu.classList.add('show');
    menuNav.classList.add('show');
   // menuBranding.classList.add('show');
    navItems.forEach(item => item.classList.add('show'));

    // Set Menu State
    showMenu = true;
  } else {
    menuBtn.classList.remove('close');
    menu.classList.remove('show');
    menuNav.classList.remove('show');
    //menuBranding.classList.remove('show');
    navItems.forEach(item => item.classList.remove('show'));

    // Set Menu State
    showMenu = false;
  }
}


//Event listener for updating the cover photo
var form = document.getElementById('upload-form');
var input = document.getElementById('upload-button');

var change_running = false;    
input.addEventListener('change', function(){
    if(!change_running){
        setTimeout(function(){
            change_running = true;
            form.submit();
            change_running = false;     
        }, 300);
    }
});
//Event listener for updating the profile photo
var form = document.getElementById('upload-profile-form');
var input = document.getElementById('upload-profile-button');

var change_running = false;    
input.addEventListener('change', function(){
    if(!change_running){
        setTimeout(function(){
            change_running = true;
            form.submit();
            change_running = false;     
        }, 300);
    }
});

