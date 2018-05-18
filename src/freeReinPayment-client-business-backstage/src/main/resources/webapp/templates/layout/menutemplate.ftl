<script id="menutemplate" type="text/html">
	<div id="menu_style" class="menu_style">
		<ul class="nav nav-list" id="nav_list">
			<!for(var i=0;i<rows.length;i++){!>
			     <!if(!rows[i].hasChildren){!>
				     <li class="home" name="<!=rows[i].url!>">
				     	<a href="javascript:void(0)"  class="iframeurl" title="" onClick="window.location.href='<!=rows[i].url!>'">
				     		<i class="<!=rows[i].mapClass!>"></i>
				     		<span class="menu-text"><!=rows[i].title!> </span>
				     	</a>
				     </li>
			     <!}!>
			     <!if(rows[i].hasChildren){!>
				     <li name="<!=rows[i].url!>">
			         	<a href="#" class="dropdown-toggle">
			         		<i class="<!=rows[i].mapClass!>"></i>
			         		<span class="menu-text"><!=rows[i].title!> </span>
			         		<b class="arrow icon-angle-down"></b>
			         	</a>
			         	<ul class="submenu">
					       <!var rows_1 = rows[i].childNodes;for(var j=0;j<rows_1.length;j++){!>
					       		<!if(!rows_1[j].hasChildren){!>
						       		<li class="home" name="<!=rows_1[j].url!>">
									 	<a href="javascript:void(0)" title="<!=rows_1[j].title!>"  class="iframeurl" onClick="window.location.href='<!=rows_1[j].url!>'">
									 		<i class="icon-double-angle-right"></i><!=rows_1[j].title!>
									 	</a>
									</li>
				                <!}!>
				                <!if(rows_1[j].hasChildren){!>
					                <li name="<!=rows_1[j].url!>">
										<a href="#" class="dropdown-toggle">
											<i class="icon-double-angle-right"></i>
											<!=rows_1[j].title!>
											<b class="arrow icon-angle-down"></b>
										</a>
										<ul class="submenu">
											<!var rows_2 = rows_1[j].childNodes;for(var m=0;m<rows_2.length;m++){!>
									       		<!if(!rows_2[m].hasChildren){!>
													<li class="home">
														<a href="#">
															<i class="<!=rows_2[m].mapClass!>"></i>
															<!=rows_2[m].title!>
														</a>
													</li>
												<!}!>
												<!if(rows_2[m].hasChildren){!>
													<li>
														<a href="#" class="dropdown-toggle">
															<i class="<!=rows_2[m].mapClass!>"></i>
															<!=rows_2[m].title!>
															<b class="arrow icon-angle-down"></b>
														</a>
														<ul class="submenu">
															<!var rows_3 = rows_2[m].childNodes;for(var n=0;n<rows_3.length;n++){!>
																<li class="home">
																	<a href="#">
																		<i class="<!=rows_3[n].mapClass!>"></i>
																		<!=rows_3[n].title!>
																	</a>
																</li>
															<!}!>
														</ul>
													</li>
												<!}!>
											<!}!>
										</ul>
					                </li>
				                <!}!>
				             <!}!>
				         </ul>       
				     </li>
			     <!}!>
			<!}!>
		</ul>
	</div>
</script>
