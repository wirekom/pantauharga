<!-- Start About us block -->
<section id="about" class="anchor_holder content_wrapper_gray">
    <div class="container content_wrapper_section">
        <div class="main_title_wrapper">
            <div class="block_tile_wrapper">
                <h1 class="block_tile_1">Top Contributors</h1>
                <div class="icon_separator"></div>
            </div>

            <div class="clear"></div>
        </div>

        <div class="container">
            <div class="row">
                <div class="span12 ranks">
                    <g:each in="${ranks}" var="rank">
                        <div>${rank.name} (${rank.posts} posts)</div>
                    </g:each>
                </div>
            </div>
        </div>
    </div>
</section> <!-- End content wrapper section -->
<!-- End About us block -->