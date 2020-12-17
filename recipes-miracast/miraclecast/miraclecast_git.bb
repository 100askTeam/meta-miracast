SUMMARY = "Connect external monitors to your system via Wifi-Display specification also known \
as Miracast."
HOMEPAGE = "https://github.com/albfan/miraclecast"
BUGTRACKER = "https://github.com/albfan/miraclecast/issues"
LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=4bccef622c3121a149752612eeb441c2 \
    file://LICENSE_htable;md5=2d5025d4aa3495befef8f17206a5b0a1 \
    file://LICENSE_gdhcp;md5=42c8401a5a2eda9c8e4419921d4e8559 \
    file://LICENSE_lgpl;md5=4fbd65380cdd255951079008b364516c \
"

DEPENDS = "glib-2.0 gstreamer1.0 systemd"

inherit autotools bash-completion pkgconfig

# Miraclecast requires systemd
REQUIRED_DISTRO_FEATURES = "systemd"

SRC_URI = "git://gitee.com/weidongshan/aml_miraclecast.git;protocol=https"
SRCREV = "68a0a7db9f1523510cdbb18330c858d60e042e7e"

S = "${WORKDIR}/git"

do_install_append() {
    install -Dm 0644 ${S}/res/org.freedesktop.miracle.conf ${D}${sysconfdir}/dbus-1/system.d/org.freedesktop.miracle.conf
    install -Dm 0755 ${S}/res/miracle-gst.sh ${D}${bindir}/miracle-gst.sh
    install -Dm 0755 ${S}/res/auto_link.sh ${D}${bindir}/auto_link.sh
    install -Dm 0755 ${S}/res/exit_miracast.sh ${D}${bindir}/exit_miracast.sh
}

PACKAGES =+ "${PN}-resources"

FILES_${PN} += " \
    ${sysconfdir}/dbus-1/system.d \
"
FILES_${PN}-resources = " \
    ${bindir}/*.sh \
"

RDEPENDS_${PN} += " \
    bash \
    iproute2 \
    gstreamer1.0-libav \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    python3-core \
"

RDEPENDS_${PN}-resources += " \
    bash \
"
