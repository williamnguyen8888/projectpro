(function (e) {
    e.flexslider = function (t, n) {
        var r = e(t), i = e.extend({}, e.flexslider.defaults, n), s = i.namespace,
            o = "ontouchstart" in window || window.DocumentTouch && document instanceof DocumentTouch,
            u = o ? "touchend" : "click", a = i.direction === "vertical", f = i.reverse, l = i.itemWidth > 0,
            c = i.animation === "fade", h = i.asNavFor !== "", p = {};
        e.data(t, "flexslider", r);
        p = {
            init: function () {
                r.animating = false;
                r.currentSlide = i.startAt;
                r.animatingTo = r.currentSlide;
                r.atEnd = r.currentSlide === 0 || r.currentSlide === r.last;
                r.containerSelector = i.selector.substr(0, i.selector.search(" "));
                r.slides = e(i.selector, r);
                r.container = e(r.containerSelector, r);
                r.count = r.slides.length;
                r.syncExists = e(i.sync).length > 0;
                if (i.animation === "slide") i.animation = "swing";
                r.prop = a ? "top" : "marginLeft";
                r.args = {};
                r.manualPause = false;
                r.transitions = !i.video && !c && i.useCSS && function () {
                    var e = document.createElement("div"),
                        t = ["perspectiveProperty", "WebkitPerspective", "MozPerspective", "OPerspective", "msPerspective"];
                    for (var n in t) {
                        if (e.style[t[n]] !== undefined) {
                            r.pfx = t[n].replace("Perspective", "").toLowerCase();
                            r.prop = "-" + r.pfx + "-transform";
                            return true
                        }
                    }
                    return false
                }();
                if (i.controlsContainer !== "") r.controlsContainer = e(i.controlsContainer).length > 0 && e(i.controlsContainer);
                if (i.manualControls !== "") r.manualControls = e(i.manualControls).length > 0 && e(i.manualControls);
                if (i.randomize) {
                    r.slides.sort(function () {
                        return Math.round(Math.random()) - .5
                    });
                    r.container.empty().append(r.slides)
                }
                r.doMath();
                if (h) p.asNav.setup();
                r.setup("init");
                if (i.controlNav) p.controlNav.setup();
                if (i.directionNav) p.directionNav.setup();
                if (i.keyboard && (e(r.containerSelector).length === 1 || i.multipleKeyboard)) {
                    e(document).bind("keyup", function (e) {
                        var t = e.keyCode;
                        if (!r.animating && (t === 39 || t === 37)) {
                            var n = t === 39 ? r.getTarget("next") : t === 37 ? r.getTarget("prev") : false;
                            r.flexAnimate(n, i.pauseOnAction)
                        }
                    })
                }
                if (i.mousewheel) {
                    r.bind("mousewheel", function (e, t, n, s) {
                        e.preventDefault();
                        var o = t < 0 ? r.getTarget("next") : r.getTarget("prev");
                        r.flexAnimate(o, i.pauseOnAction)
                    })
                }
                if (i.pausePlay) p.pausePlay.setup();
                if (i.slideshow) {
                    if (i.pauseOnHover) {
                        r.hover(function () {
                            if (!r.manualPlay && !r.manualPause) r.pause()
                        }, function () {
                            if (!r.manualPause && !r.manualPlay) r.play()
                        })
                    }
                    i.initDelay > 0 ? setTimeout(r.play, i.initDelay) : r.play()
                }
                if (o && i.touch) p.touch();
                if (!c || c && i.smoothHeight) e(window).bind("resize focus", p.resize);
                setTimeout(function () {
                    i.start(r)
                }, 200)
            }, asNav: {
                setup: function () {
                    r.asNav = true;
                    r.animatingTo = Math.floor(r.currentSlide / r.move);
                    r.currentItem = r.currentSlide;
                    r.slides.removeClass(s + "active-slide").eq(r.currentItem).addClass(s + "active-slide");
                    r.slides.click(function (t) {
                        t.preventDefault();
                        var n = e(this), s = n.index();
                        if (!e(i.asNavFor).data("flexslider").animating && !n.hasClass("active")) {
                            r.direction = r.currentItem < s ? "next" : "prev";
                            r.flexAnimate(s, i.pauseOnAction, false, true, true)
                        }
                    })
                }
            }, controlNav: {
                setup: function () {
                    if (!r.manualControls) {
                        p.controlNav.setupPaging()
                    } else {
                        p.controlNav.setupManual()
                    }
                }, setupPaging: function () {
                    var t = i.controlNav === "thumbnails" ? "control-thumbs" : "control-paging", n = 1, a;
                    r.controlNavScaffold = e('<ol class="' + s + "control-nav " + s + t + '"></ol>');
                    if (r.pagingCount > 1) {
                        for (var f = 0; f < r.pagingCount; f++) {
                            a = i.controlNav === "thumbnails" ? '<img src="' + r.slides.eq(f).attr("data-thumb") + '"/>' : "<a>" + n + "</a>";
                            r.controlNavScaffold.append("<li>" + a + "</li>");
                            n++
                        }
                    }
                    r.controlsContainer ? e(r.controlsContainer).append(r.controlNavScaffold) : r.append(r.controlNavScaffold);
                    p.controlNav.set();
                    p.controlNav.active();
                    r.controlNavScaffold.delegate("a, img", u, function (t) {
                        t.preventDefault();
                        var n = e(this), o = r.controlNav.index(n);
                        if (!n.hasClass(s + "active")) {
                            r.direction = o > r.currentSlide ? "next" : "prev";
                            r.flexAnimate(o, i.pauseOnAction)
                        }
                    });
                    if (o) {
                        r.controlNavScaffold.delegate("a", "click touchstart", function (e) {
                            e.preventDefault()
                        })
                    }
                }, setupManual: function () {
                    r.controlNav = r.manualControls;
                    p.controlNav.active();
                    r.controlNav.live(u, function (t) {
                        t.preventDefault();
                        var n = e(this), o = r.controlNav.index(n);
                        if (!n.hasClass(s + "active")) {
                            o > r.currentSlide ? r.direction = "next" : r.direction = "prev";
                            r.flexAnimate(o, i.pauseOnAction)
                        }
                    });
                    if (o) {
                        r.controlNav.live("click touchstart", function (e) {
                            e.preventDefault()
                        })
                    }
                }, set: function () {
                    var t = i.controlNav === "thumbnails" ? "img" : "a";
                    r.controlNav = e("." + s + "control-nav li " + t, r.controlsContainer ? r.controlsContainer : r)
                }, active: function () {
                    r.controlNav.removeClass(s + "active").eq(r.animatingTo).addClass(s + "active")
                }, update: function (t, n) {
                    if (r.pagingCount > 1 && t === "add") {
                        r.controlNavScaffold.append(e("<li><a>" + r.count + "</a></li>"))
                    } else if (r.pagingCount === 1) {
                        r.controlNavScaffold.find("li").remove()
                    } else {
                        r.controlNav.eq(n).closest("li").remove()
                    }
                    p.controlNav.set();
                    r.pagingCount > 1 && r.pagingCount !== r.controlNav.length ? r.update(n, t) : p.controlNav.active()
                }
            }, directionNav: {
                setup: function () {
                    var t = e('<ul class="' + s + 'direction-nav"><li><a class="' + s + 'prev" href="#">' + i.prevText + '</a></li><li><a class="' + s + 'next" href="#">' + i.nextText + "</a></li></ul>");
                    if (r.controlsContainer) {
                        e(r.controlsContainer).append(t);
                        r.directionNav = e("." + s + "direction-nav li a", r.controlsContainer)
                    } else {
                        r.append(t);
                        r.directionNav = e("." + s + "direction-nav li a", r)
                    }
                    p.directionNav.update();
                    r.directionNav.bind(u, function (t) {
                        t.preventDefault();
                        var n = e(this).hasClass(s + "next") ? r.getTarget("next") : r.getTarget("prev");
                        r.flexAnimate(n, i.pauseOnAction)
                    });
                    if (o) {
                        r.directionNav.bind("click touchstart", function (e) {
                            e.preventDefault()
                        })
                    }
                }, update: function () {
                    var e = s + "disabled";
                    if (r.pagingCount === 1) {
                        r.directionNav.addClass(e)
                    } else if (!i.animationLoop) {
                        if (r.animatingTo === 0) {
                            r.directionNav.removeClass(e).filter("." + s + "prev").addClass(e)
                        } else if (r.animatingTo === r.last) {
                            r.directionNav.removeClass(e).filter("." + s + "next").addClass(e)
                        } else {
                            r.directionNav.removeClass(e)
                        }
                    } else {
                        r.directionNav.removeClass(e)
                    }
                }
            }, pausePlay: {
                setup: function () {
                    var t = e('<div class="' + s + 'pauseplay"><a></a></div>');
                    if (r.controlsContainer) {
                        r.controlsContainer.append(t);
                        r.pausePlay = e("." + s + "pauseplay a", r.controlsContainer)
                    } else {
                        r.append(t);
                        r.pausePlay = e("." + s + "pauseplay a", r)
                    }
                    p.pausePlay.update(i.slideshow ? s + "pause" : s + "play");
                    r.pausePlay.bind(u, function (t) {
                        t.preventDefault();
                        if (e(this).hasClass(s + "pause")) {
                            r.manualPause = true;
                            r.manualPlay = false;
                            r.pause()
                        } else {
                            r.manualPause = false;
                            r.manualPlay = true;
                            r.play()
                        }
                    });
                    if (o) {
                        r.pausePlay.bind("click touchstart", function (e) {
                            e.preventDefault()
                        })
                    }
                }, update: function (e) {
                    e === "play" ? r.pausePlay.removeClass(s + "pause").addClass(s + "play").text(i.playText) : r.pausePlay.removeClass(s + "play").addClass(s + "pause").text(i.pauseText)
                }
            }, touch: function () {
                function d(u) {
                    if (r.animating) {
                        u.preventDefault()
                    } else if (u.touches.length === 1) {
                        r.pause();
                        o = a ? r.h : r.w;
                        h = Number(new Date);
                        s = l && f && r.animatingTo === r.last ? 0 : l && f ? r.limit - (r.itemW + i.itemMargin) * r.move * r.animatingTo : l && r.currentSlide === r.last ? r.limit : l ? (r.itemW + i.itemMargin) * r.move * r.currentSlide : f ? (r.last - r.currentSlide + r.cloneOffset) * o : (r.currentSlide + r.cloneOffset) * o;
                        e = a ? u.touches[0].pageY : u.touches[0].pageX;
                        n = a ? u.touches[0].pageX : u.touches[0].pageY;
                        t.addEventListener("touchmove", v, false);
                        t.addEventListener("touchend", m, false)
                    }
                }

                function v(t) {
                    u = a ? e - t.touches[0].pageY : e - t.touches[0].pageX;
                    p = a ? Math.abs(u) < Math.abs(t.touches[0].pageX - n) : Math.abs(u) < Math.abs(t.touches[0].pageY - n);
                    if (!p || Number(new Date) - h > 500) {
                        t.preventDefault();
                        if (!c && r.transitions) {
                            if (!i.animationLoop) {
                                u = u / (r.currentSlide === 0 && u < 0 || r.currentSlide === r.last && u > 0 ? Math.abs(u) / o + 2 : 1)
                            }
                            r.setProps(s + u, "setTouch")
                        }
                    }
                }

                function m(a) {
                    t.removeEventListener("touchmove", v, false);
                    if (r.animatingTo === r.currentSlide && !p && !(u === null)) {
                        var l = f ? -u : u, d = l > 0 ? r.getTarget("next") : r.getTarget("prev");
                        if (r.canAdvance(d) && (Number(new Date) - h < 550 && Math.abs(l) > 50 || Math.abs(l) > o / 2)) {
                            r.flexAnimate(d, i.pauseOnAction)
                        } else {
                            if (!c) r.flexAnimate(r.currentSlide, i.pauseOnAction, true)
                        }
                    }
                    t.removeEventListener("touchend", m, false);
                    e = null;
                    n = null;
                    u = null;
                    s = null
                }

                var e, n, s, o, u, h, p = false;
                t.addEventListener("touchstart", d, false)
            }, resize: function () {
                if (!r.animating && r.is(":visible")) {
                    if (!l) r.doMath();
                    if (c) {
                        p.smoothHeight()
                    } else if (l) {
                        r.slides.width(r.computedW);
                        r.update(r.pagingCount);
                        r.setProps()
                    } else if (a) {
                        r.viewport.height(r.h);
                        r.setProps(r.h, "setTotal")
                    } else {
                        if (i.smoothHeight) p.smoothHeight();
                        r.newSlides.width(r.computedW);
                        r.setProps(r.computedW, "setTotal")
                    }
                }
            }, smoothHeight: function (e) {
                if (!a || c) {
                    var t = c ? r : r.viewport;
                    e ? t.animate({height: r.slides.eq(r.animatingTo).height()}, e) : t.height(r.slides.eq(r.animatingTo).height())
                }
            }, sync: function (t) {
                var n = e(i.sync).data("flexslider"), s = r.animatingTo;
                switch (t) {
                    case"animate":
                        n.flexAnimate(s, i.pauseOnAction, false, true);
                        break;
                    case"play":
                        if (!n.playing && !n.asNav) {
                            n.play()
                        }
                        break;
                    case"pause":
                        n.pause();
                        break
                }
            }
        };
        r.flexAnimate = function (t, n, u, d, v) {
            if (h && r.pagingCount === 1) r.direction = r.currentItem < t ? "next" : "prev";
            if (!r.animating && (r.canAdvance(t, v) || u) && r.is(":visible")) {
                if (h && d) {
                    var m = e(i.asNavFor).data("flexslider");
                    r.atEnd = t === 0 || t === r.count - 1;
                    m.flexAnimate(t, true, false, true, v);
                    r.direction = r.currentItem < t ? "next" : "prev";
                    m.direction = r.direction;
                    if (Math.ceil((t + 1) / r.visible) - 1 !== r.currentSlide && t !== 0) {
                        r.currentItem = t;
                        r.slides.removeClass(s + "active-slide").eq(t).addClass(s + "active-slide");
                        t = Math.floor(t / r.visible)
                    } else {
                        r.currentItem = t;
                        r.slides.removeClass(s + "active-slide").eq(t).addClass(s + "active-slide");
                        return false
                    }
                }
                r.animating = true;
                r.animatingTo = t;
                i.before(r);
                if (n) r.pause();
                if (r.syncExists && !v) p.sync("animate");
                if (i.controlNav) p.controlNav.active();
                if (!l) r.slides.removeClass(s + "active-slide").eq(t).addClass(s + "active-slide");
                r.atEnd = t === 0 || t === r.last;
                if (i.directionNav) p.directionNav.update();
                if (t === r.last) {
                    i.end(r);
                    if (!i.animationLoop) r.pause()
                }
                if (!c) {
                    var g = a ? r.slides.filter(":first").height() : r.computedW, y, b, w;
                    if (l) {
                        y = i.itemWidth > r.w ? i.itemMargin * 2 : i.itemMargin;
                        w = (r.itemW + y) * r.move * r.animatingTo;
                        b = w > r.limit && r.visible !== 1 ? r.limit : w
                    } else if (r.currentSlide === 0 && t === r.count - 1 && i.animationLoop && r.direction !== "next") {
                        b = f ? (r.count + r.cloneOffset) * g : 0
                    } else if (r.currentSlide === r.last && t === 0 && i.animationLoop && r.direction !== "prev") {
                        b = f ? 0 : (r.count + 1) * g
                    } else {
                        b = f ? (r.count - 1 - t + r.cloneOffset) * g : (t + r.cloneOffset) * g
                    }
                    r.setProps(b, "", i.animationSpeed);
                    if (r.transitions) {
                        if (!i.animationLoop || !r.atEnd) {
                            r.animating = false;
                            r.currentSlide = r.animatingTo
                        }
                        r.container.unbind("webkitTransitionEnd transitionend");
                        r.container.bind("webkitTransitionEnd transitionend", function () {
                            r.wrapup(g)
                        })
                    } else {
                        r.container.animate(r.args, i.animationSpeed, i.easing, function () {
                            r.wrapup(g)
                        })
                    }
                } else {
                    if (!o) {
                        r.slides.eq(r.currentSlide).fadeOut(i.animationSpeed, i.easing);
                        r.slides.eq(t).fadeIn(i.animationSpeed, i.easing, r.wrapup)
                    } else {
                        r.slides.eq(r.currentSlide).css({opacity: 0});
                        r.slides.eq(t).css({opacity: 1});
                        r.animating = false;
                        r.currentSlide = r.animatingTo
                    }
                }
                if (i.smoothHeight) p.smoothHeight(i.animationSpeed)
            }
        };
        r.wrapup = function (e) {
            if (!c && !l) {
                if (r.currentSlide === 0 && r.animatingTo === r.last && i.animationLoop) {
                    r.setProps(e, "jumpEnd")
                } else if (r.currentSlide === r.last && r.animatingTo === 0 && i.animationLoop) {
                    r.setProps(e, "jumpStart")
                }
            }
            r.animating = false;
            r.currentSlide = r.animatingTo;
            i.after(r)
        };
        r.animateSlides = function () {
            if (!r.animating) r.flexAnimate(r.getTarget("next"))
        };
        r.pause = function () {
            clearInterval(r.animatedSlides);
            r.playing = false;
            if (i.pausePlay) p.pausePlay.update("play");
            if (r.syncExists) p.sync("pause")
        };
        r.play = function () {
            r.animatedSlides = setInterval(r.animateSlides, i.slideshowSpeed);
            r.playing = true;
            if (i.pausePlay) p.pausePlay.update("pause");
            if (r.syncExists) p.sync("play")
        };
        r.canAdvance = function (e, t) {
            var n = h ? r.pagingCount - 1 : r.last;
            return t ? true : h && r.currentItem === r.count - 1 && e === 0 && r.direction === "prev" ? true : h && r.currentItem === 0 && e === r.pagingCount - 1 && r.direction !== "next" ? false : e === r.currentSlide && !h ? false : i.animationLoop ? true : r.atEnd && r.currentSlide === 0 && e === n && r.direction !== "next" ? false : r.atEnd && r.currentSlide === n && e === 0 && r.direction === "next" ? false : true
        };
        r.getTarget = function (e) {
            r.direction = e;
            if (e === "next") {
                return r.currentSlide === r.last ? 0 : r.currentSlide + 1
            } else {
                return r.currentSlide === 0 ? r.last : r.currentSlide - 1
            }
        };
        r.setProps = function (e, t, n) {
            var s = function () {
                var n = e ? e : (r.itemW + i.itemMargin) * r.move * r.animatingTo, s = function () {
                    if (l) {
                        return t === "setTouch" ? e : f && r.animatingTo === r.last ? 0 : f ? r.limit - (r.itemW + i.itemMargin) * r.move * r.animatingTo : r.animatingTo === r.last ? r.limit : n
                    } else {
                        switch (t) {
                            case"setTotal":
                                return f ? (r.count - 1 - r.currentSlide + r.cloneOffset) * e : (r.currentSlide + r.cloneOffset) * e;
                            case"setTouch":
                                return f ? e : e;
                            case"jumpEnd":
                                return f ? e : r.count * e;
                            case"jumpStart":
                                return f ? r.count * e : e;
                            default:
                                return e
                        }
                    }
                }();
                return s * -1 + "px"
            }();
            if (r.transitions) {
                s = a ? "translate3d(0," + s + ",0)" : "translate3d(" + s + ",0,0)";
                n = n !== undefined ? n / 1e3 + "s" : "0s";
                r.container.css("-" + r.pfx + "-transition-duration", n)
            }
            r.args[r.prop] = s;
            if (r.transitions || n === undefined) r.container.css(r.args)
        };
        r.setup = function (t) {
            if (!c) {
                var n, u;
                if (t === "init") {
                    r.viewport = e('<div class="' + s + 'viewport"></div>').css({
                        overflow: "hidden",
                        position: "relative"
                    }).appendTo(r).append(r.container);
                    r.cloneCount = 0;
                    r.cloneOffset = 0;
                    if (f) {
                        u = e.makeArray(r.slides).reverse();
                        r.slides = e(u);
                        r.container.empty().append(r.slides)
                    }
                }
                if (i.animationLoop && !l) {
                    r.cloneCount = 2;
                    r.cloneOffset = 1;
                    if (t !== "init") r.container.find(".clone").remove();
                    r.container.append(r.slides.first().clone().addClass("clone")).prepend(r.slides.last().clone().addClass("clone"))
                }
                r.newSlides = e(i.selector, r);
                n = f ? r.count - 1 - r.currentSlide + r.cloneOffset : r.currentSlide + r.cloneOffset;
                if (a && !l) {
                    r.container.height((r.count + r.cloneCount) * 200 + "%").css("position", "absolute").width("100%");
                    setTimeout(function () {
                        r.newSlides.css({display: "block"});
                        r.doMath();
                        r.viewport.height(r.h);
                        r.setProps(n * r.h, "init")
                    }, t === "init" ? 100 : 0)
                } else {
                    r.container.width((r.count + r.cloneCount) * 200 + "%");
                    r.setProps(n * r.computedW, "init");
                    setTimeout(function () {
                        r.doMath();
                        r.newSlides.css({width: r.computedW, "float": "left", display: "block"});
                        if (i.smoothHeight) p.smoothHeight()
                    }, t === "init" ? 100 : 0)
                }
            } else {
                r.slides.css({width: "100%", "float": "left", marginRight: "-100%", position: "relative"});
                if (t === "init") {
                    if (!o) {
                        r.slides.eq(r.currentSlide).fadeIn(i.animationSpeed, i.easing)
                    } else {
                        r.slides.css({
                            opacity: 0,
                            display: "block",
                            webkitTransition: "opacity " + i.animationSpeed / 1e3 + "s ease"
                        }).eq(r.currentSlide).css({opacity: 1})
                    }
                }
                if (i.smoothHeight) p.smoothHeight()
            }
            if (!l) r.slides.removeClass(s + "active-slide").eq(r.currentSlide).addClass(s + "active-slide")
        };
        r.doMath = function () {
            var e = r.slides.first(), t = i.itemMargin, n = i.minItems, s = i.maxItems;
            r.w = r.width();
            r.h = e.height();
            r.boxPadding = e.outerWidth() - e.width();
            if (l) {
                r.itemT = i.itemWidth + t;
                r.minW = n ? n * r.itemT : r.w;
                r.maxW = s ? s * r.itemT : r.w;
                r.itemW = r.minW > r.w ? (r.w - t * n) / n : r.maxW < r.w ? (r.w - t * s) / s : i.itemWidth > r.w ? r.w : i.itemWidth;
                r.visible = Math.floor(r.w / (r.itemW + t));
                r.move = i.move > 0 && i.move < r.visible ? i.move : r.visible;
                r.pagingCount = Math.ceil((r.count - r.visible) / r.move + 1);
                r.last = r.pagingCount - 1;
                r.limit = r.pagingCount === 1 ? 0 : i.itemWidth > r.w ? (r.itemW + t * 2) * r.count - r.w - t : (r.itemW + t) * r.count - r.w - t
            } else {
                r.itemW = r.w;
                r.pagingCount = r.count;
                r.last = r.count - 1
            }
            r.computedW = r.itemW - r.boxPadding
        };
        r.update = function (e, t) {
            r.doMath();
            if (!l) {
                if (e < r.currentSlide) {
                    r.currentSlide += 1
                } else if (e <= r.currentSlide && e !== 0) {
                    r.currentSlide -= 1
                }
                r.animatingTo = r.currentSlide
            }
            if (i.controlNav && !r.manualControls) {
                if (t === "add" && !l || r.pagingCount > r.controlNav.length) {
                    p.controlNav.update("add")
                } else if (t === "remove" && !l || r.pagingCount < r.controlNav.length) {
                    if (l && r.currentSlide > r.last) {
                        r.currentSlide -= 1;
                        r.animatingTo -= 1
                    }
                    p.controlNav.update("remove", r.last)
                }
            }
            if (i.directionNav) p.directionNav.update()
        };
        r.addSlide = function (t, n) {
            var s = e(t);
            r.count += 1;
            r.last = r.count - 1;
            if (a && f) {
                n !== undefined ? r.slides.eq(r.count - n).after(s) : r.container.prepend(s)
            } else {
                n !== undefined ? r.slides.eq(n).before(s) : r.container.append(s)
            }
            r.update(n, "add");
            r.slides = e(i.selector + ":not(.clone)", r);
            r.setup();
            i.added(r)
        };
        r.removeSlide = function (t) {
            var n = isNaN(t) ? r.slides.index(e(t)) : t;
            r.count -= 1;
            r.last = r.count - 1;
            if (isNaN(t)) {
                e(t, r.slides).remove()
            } else {
                a && f ? r.slides.eq(r.last).remove() : r.slides.eq(t).remove()
            }
            r.doMath();
            r.update(n, "remove");
            r.slides = e(i.selector + ":not(.clone)", r);
            r.setup();
            i.removed(r)
        };
        p.init()
    };
    e.flexslider.defaults = {
        namespace: "flex-",
        selector: ".slides > li",
        animation: "fade",
        easing: "swing",
        direction: "horizontal",
        reverse: false,
        animationLoop: true,
        smoothHeight: false,
        startAt: 0,
        slideshow: true,
        slideshowSpeed: 7e3,
        animationSpeed: 600,
        initDelay: 0,
        randomize: false,
        pauseOnAction: true,
        pauseOnHover: false,
        useCSS: true,
        touch: true,
        video: false,
        controlNav: true,
        directionNav: true,
        prevText: "",
        nextText: "",
        keyboard: true,
        multipleKeyboard: false,
        mousewheel: false,
        pausePlay: false,
        pauseText: "Pause",
        playText: "Play",
        controlsContainer: "",
        manualControls: "",
        sync: "",
        asNavFor: "",
        itemWidth: 0,
        itemMargin: 0,
        minItems: 0,
        maxItems: 0,
        move: 0,
        start: function () {
        },
        before: function () {
        },
        after: function () {
        },
        end: function () {
        },
        added: function () {
        },
        removed: function () {
        }
    };
    e.fn.flexslider = function (t) {
        if (t === undefined) t = {};
        if (typeof t === "object") {
            return this.each(function () {
                var n = e(this), r = t.selector ? t.selector : ".slides > li", i = n.find(r);
                if (i.length === 1) {
                    i.fadeIn(400);
                    if (t.start) t.start(n)
                } else if (n.data("flexslider") === undefined) {
                    new e.flexslider(this, t)
                }
            })
        } else {
            var n = e(this).data("flexslider");
            switch (t) {
                case"play":
                    n.play();
                    break;
                case"pause":
                    n.pause();
                    break;
                case"next":
                    n.flexAnimate(n.getTarget("next"), true);
                    break;
                case"prev":
                case"previous":
                    n.flexAnimate(n.getTarget("prev"), true);
                    break;
                default:
                    if (typeof t === "number") n.flexAnimate(t, true)
            }
        }
    }
})(jQuery)